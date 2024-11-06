package intellispaces.common.base.type;

import intellispaces.common.base.collection.ArraysFunctions;
import intellispaces.common.base.collection.CollectionFunctions;
import intellispaces.common.base.exception.UnexpectedViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Class related functions.
 */
public class ClassFunctions {
  private static final Logger LOG = LoggerFactory.getLogger(ClassFunctions.class);

  public static Optional<Class<?>> getClass(String className) {
    try {
      return Optional.of(Class.forName(className));
    } catch (ClassNotFoundException e) {
      return Optional.empty();
    }
  }

  @SuppressWarnings("unchecked")
  public static <F> F getStaticField(
      Class<?> aClass, String fieldName, Class<F> fieldClass
  ) throws NoSuchFieldException, IllegalAccessException {
    Field field = aClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    return (F) field.get(null);
  }

  public static void setStaticField(
      Class<?> aClass, String fieldName, Object newValue
  ) throws NoSuchFieldException, IllegalAccessException {
    Field field = aClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(null, newValue);
  }

  public static Class<?> getClassOrElseThrow(String className) {
    return getClass(className).orElseThrow(() -> UnexpectedViolationException.withMessage(
        "Could not to get class by name {0}", className));
  }

  public static <E extends Throwable> Class<?> getClassOrElseThrow(
      String className, Supplier<? extends E> exceptionSupplier
  ) throws E {
    return getClass(className).orElseThrow(exceptionSupplier);
  }

  public static boolean hasAnnotationDeep(Class<?> aClass, Class<? extends Annotation> annotation) {
    if (aClass.isAnnotationPresent(annotation)) {
      return true;
    }
    if (aClass.getSuperclass() != null && hasAnnotationDeep(aClass.getSuperclass(), annotation)) {
      return true;
    }
    for (Class<?> interfaceClass : aClass.getInterfaces()) {
      if (hasAnnotationDeep(interfaceClass, annotation)) {
        return true;
      }
    }
    return false;
  }

  public static Optional<String> getJavaLibraryName(Class<?> aClass) {
    try {
      return Optional.of(new File(aClass.getProtectionDomain().getCodeSource().getLocation().getPath()).getName());
    } catch (Exception e) {
      LOG.debug("Could not get Java library name", e);
      return Optional.empty();
    }
  }

  public static boolean isAbstractClass(Class<?> aClass) {
    return Modifier.isAbstract(aClass.getModifiers());
  }

  public static boolean isFinalClass(Class<?> aClass) {
    return Modifier.isFinal(aClass.getModifiers());
  }

  public static Class<?> getObjectClass(Class<?> aClass) {
    if (!aClass.isPrimitive()) {
      return aClass;
    }
    if (aClass == boolean.class) {
      return Boolean.class;
    } else if (aClass == byte.class) {
      return Byte.class;
    } else if (aClass == short.class) {
      return Short.class;
    } else if (aClass == int.class) {
      return Integer.class;
    } else if (aClass == long.class) {
      return Long.class;
    } else if (aClass == char.class) {
      return Character.class;
    } else if (aClass == float.class) {
      return Float.class;
    } else if (aClass == double.class) {
      return Double.class;
    } else if (aClass == void.class) {
      return Void.class;
    }
    return aClass;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getDefaultValueOf(Class<T> type) {
    return (T) DEFAULT_VALUES.get(type);
  }

  public static Class<?> getPrimitiveWrapperClass(String primitiveType) {
    Class<?> primitiveClass = PRIMITIVE_TO_WRAPPER_CLASSES_MAP.get(primitiveType);
    if (primitiveClass == null) {
      throw UnexpectedViolationException.withMessage("Not primitive typename: {0}", primitiveType);
    }
    return primitiveClass;
  }

  public static List<Class<?>> getParents(Class<?> aClass) {
    var result = new ArrayList<Class<?>>();
    ArraysFunctions.foreach(aClass.getInterfaces(), result::add);
    CollectionFunctions.addIfNotNull(result, aClass.getSuperclass());
    return result;
  }

  public static boolean isStandardClass(Class<?> aClass) {
    return isStandardClassName(aClass.getCanonicalName());
  }

  public static boolean isStandardClassName(String classCanonicalName) {
    if (classCanonicalName.startsWith("java.lang.")) {
      String substring = classCanonicalName.substring(10);
      return !substring.isEmpty() && !substring.contains(".");
    }
    return false;
  }

  public static boolean isBooleanClass(String classCanonicalName) {
    return Boolean.class.getCanonicalName().equals(classCanonicalName)
        || boolean.class.getCanonicalName().equals(classCanonicalName);
  }

  public static boolean isDoubleClass(String classCanonicalName) {
    return Double.class.getCanonicalName().equals(classCanonicalName)
        || double.class.getCanonicalName().equals(classCanonicalName);
  }

  public static String getPrimitiveTypeOfWrapper(String wrapperCanonicalName) {
    String primitiveType = WRAPPER_CLASS_TO_PRIMITIVE_MAP.get(wrapperCanonicalName);
    if (primitiveType == null) {
      throw UnexpectedViolationException.withMessage("Not primitive wrapper: {0}", wrapperCanonicalName);
    }
    return primitiveType;
  }

  public static Optional<Primitive> primitiveByWrapperClassName(String canonicalName) {
    Primitive primitive = null;
    for (Primitive p : Primitives.values()) {
      if (p.wrapperClass().getCanonicalName().equals(canonicalName)) {
        primitive = p;
        break;
      }
    }
    return Optional.ofNullable(primitive);
  }

  public static boolean isPrimitiveWrapperClass(String classCanonicalName) {
    return WRAPPER_CLASS_TO_PRIMITIVE_MAP.containsKey(classCanonicalName);
  }

  private ClassFunctions() {}

  private static final Map<Class<?>, Object> DEFAULT_VALUES = new HashMap<>();
  static {
    DEFAULT_VALUES.put(boolean.class, false);
    DEFAULT_VALUES.put(char.class, '\u0000');
    DEFAULT_VALUES.put(byte.class, (byte) 0);
    DEFAULT_VALUES.put(short.class, (short) 0);
    DEFAULT_VALUES.put(int.class, 0);
    DEFAULT_VALUES.put(long.class, 0L);
    DEFAULT_VALUES.put(float.class, 0.0f);
    DEFAULT_VALUES.put(double.class, 0.0);

    DEFAULT_VALUES.put(Boolean.class, false);
    DEFAULT_VALUES.put(Character.class, '\u0000');
    DEFAULT_VALUES.put(Byte.class, (byte) 0);
    DEFAULT_VALUES.put(Short.class, (short) 0);
    DEFAULT_VALUES.put(Integer.class, 0);
    DEFAULT_VALUES.put(Long.class, 0L);
    DEFAULT_VALUES.put(Float.class, 0.0f);
    DEFAULT_VALUES.put(Double.class, 0.0);
  }

  private static final Map<String, String> WRAPPER_CLASS_TO_PRIMITIVE_MAP = Map.of(
      Boolean.class.getCanonicalName(), "boolean",
      Character.class.getCanonicalName(), "char",
      Byte.class.getCanonicalName(), "byte",
      Short.class.getCanonicalName(), "short",
      Integer.class.getCanonicalName(), "int",
      Long.class.getCanonicalName(), "long",
      Float.class.getCanonicalName(), "float",
      Double.class.getCanonicalName(), "double"
  );

  private static final Map<String, Class<?>> PRIMITIVE_TO_WRAPPER_CLASSES_MAP = Map.of(
      "boolean", Boolean.class,
      "char", Character.class,
      "byte", Byte.class,
      "short", Short.class,
      "int", Integer.class,
      "long", Long.class,
      "float", Float.class,
      "double", Double.class
  );
}
