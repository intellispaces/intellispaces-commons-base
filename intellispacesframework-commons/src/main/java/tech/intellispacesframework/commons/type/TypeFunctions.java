package tech.intellispacesframework.commons.type;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Type related functions.
 */
public class TypeFunctions {

  public static String getSimpleName(String canonicalName) {
    if (canonicalName.isEmpty()) {
      throw UnexpectedViolationException.withMessage("Class canonical name can't be empty");
    }
    int lastDot = canonicalName.lastIndexOf('.');
    return canonicalName.substring(lastDot + 1);
  }

  public static String getPackageName(String canonicalName) {
    if (canonicalName.isEmpty()) {
      throw UnexpectedViolationException.withMessage("Class canonical name can't be empty");
    }
    int lastDot = canonicalName.lastIndexOf('.');
    return lastDot > 0 ? canonicalName.substring(0, lastDot) : "";
  }

  public static boolean isAbstractClass(Class<?> aClass) {
    return Modifier.isAbstract(aClass.getModifiers());
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
  public static <T> T getAnyValidValueOfClass(Class<T> type) {
    return (T) DEFAULT_VALUES.get(type);
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
}
