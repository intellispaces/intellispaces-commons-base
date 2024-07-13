package tech.intellispaces.framework.commons.type;

import tech.intellispaces.framework.commons.exception.UnexpectedViolationException;

import javax.lang.model.element.Element;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Type related functions.
 */
public class TypeFunctions {

  public static Optional<Class<?>> getClass(String className) {
    try {
      return Optional.of(Class.forName(className));
    } catch (ClassNotFoundException e) {
      return Optional.empty();
    }
  }

  public static Optional<Method> getMethod(Class<?> aClass, String name, Class<?>... parameterTypes) {
    try {
      return Optional.of(aClass.getMethod(name, parameterTypes));
    } catch (NoSuchMethodException e) {
      return Optional.empty();
    }
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

  public static Optional<Method> getMethod(Class<?> aClass, String methodName) {
    try {
      return Optional.of(aClass.getMethod(methodName));
    } catch (NoSuchMethodException e) {
      return Optional.empty();
    }
  }

  public static String getJavaLibraryName(Class<?> aClass) {
    try {
      return new File(aClass.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
    } catch (Exception e) {
      return "unknown";
    }
  }

  /**
   * Extract simple name.
   *
   * @param name name obtained from methods Class#getName or Class#getCanonicalName
   * @return simple name.
   */
  public static String getSimpleName(String name) {
    if (name.isEmpty()) {
      throw UnexpectedViolationException.withMessage("Class canonical name should be not empty");
    }
    int lastDot = name.lastIndexOf('.');
    int lastDollar = name.lastIndexOf('$');
    return name.substring(Math.max(lastDot, lastDollar) + 1);
  }

  /**
   * Extract package name.
   *
   * @param className class name obtained from method Class#getName
   * @return package name.
   */
  public static String getPackageName(String className) {
    if (className.isEmpty()) {
      throw UnexpectedViolationException.withMessage("Class name should be not empty");
    }
    int lastDot = className.lastIndexOf('.');
    return lastDot > 0 ? className.substring(0, lastDot) : "";
  }

  public static String joinPackageAndSimpleName(String packageName, String simpleName) {
    if (simpleName == null || simpleName.isBlank()) {
      throw UnexpectedViolationException.withMessage("Class canonical name should be not empty");
    }
    if (packageName == null || packageName.isEmpty()) {
      return simpleName;
    } else {
      return packageName + "." + simpleName;
    }
  }

  public static String replaceSimpleName(String canonicalName, String newSimpleName) {
    if (canonicalName == null || canonicalName.isBlank()) {
      throw UnexpectedViolationException.withMessage("Class canonical name should be not empty");
    }
    if (newSimpleName == null || newSimpleName.isBlank()) {
      throw UnexpectedViolationException.withMessage("Class simple name should be not empty");
    }
    return joinPackageAndSimpleName(getPackageName(canonicalName), newSimpleName);
  }

  public static String addPrefixToClassName(String prefix, String canonicalName) {
    String packageName = getPackageName(canonicalName);
    return packageName + (packageName.isEmpty() ? "" : ".") + prefix + getSimpleName(canonicalName);
  }

  public static boolean isAbstractClass(Class<?> aClass) {
    return Modifier.isAbstract(aClass.getModifiers());
  }

  public static boolean isAbstractElement(Element element) {
    return element.getModifiers().contains(javax.lang.model.element.Modifier.ABSTRACT);
  }

  public static boolean isAbstractMethod(Method method) {
    return Modifier.isAbstract(method.getModifiers());
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
    Class<?> primitiveClass = WRAPPER_CLASSES.get(primitiveType);
    if (primitiveClass == null) {
      throw UnexpectedViolationException.withMessage("Not primitive type: {}", primitiveType);
    }
    return primitiveClass;
  }

  @SuppressWarnings("unchecked")
  public static <T extends C, C> Class<T> typedClass(Class<C> aClass) {
    return (Class<T>) aClass;
  }

  private TypeFunctions() {}

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

  private final static Map<String, Class<?>> WRAPPER_CLASSES = Map.of(
      "boolean", Boolean.class,
      "byte", Byte.class,
      "short", Short.class,
      "int", Integer.class,
      "long", Long.class,
      "float", Float.class,
      "double", Double.class,
      "char", Character.class
  );
}
