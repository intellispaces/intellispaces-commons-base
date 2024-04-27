package tech.intellispacesframework.commons.classes;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;

/**
 * Class related functions.
 */
public interface ClassFunctions {

  static String getSimpleName(String canonicalName) {
    if (canonicalName.isEmpty()) {
      throw UnexpectedViolationException.withMessage("Class canonical name can't be empty");
    }
    int lastDot = canonicalName.lastIndexOf('.');
    return canonicalName.substring(lastDot + 1);
  }

  static String getPackageName(String canonicalName) {
    if (canonicalName.isEmpty()) {
      throw UnexpectedViolationException.withMessage("Class canonical name can't be empty");
    }
    int lastDot = canonicalName.lastIndexOf('.');
    return lastDot > 0 ? canonicalName.substring(0, lastDot) : "";
  }

  static Class<?> getObjectClass(Class<?> aClass) {
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
}
