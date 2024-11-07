package intellispaces.common.base.type;

import intellispaces.common.base.exception.UnexpectedExceptions;

/**
 * Class name related functions.
 */
public interface ClassNameFunctions {

  /**
   * Extract simple name.
   *
   * @param name name obtained from methods Class#getName or Class#getCanonicalName
   * @return simple name.
   */
  static String getSimpleName(String name) {
    if (name.isEmpty()) {
      throw UnexpectedExceptions.withMessage("Class canonical name should be not empty");
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
  static String getPackageName(String className) {
    if (className.isEmpty()) {
      throw UnexpectedExceptions.withMessage("Class name should be not empty");
    }
    int lastDot = className.lastIndexOf('.');
    return lastDot > 0 ? className.substring(0, lastDot) : "";
  }

  static String getShortenName(String canonicalName) {
    if (canonicalName.startsWith("java.lang.")) {
      return getSimpleName(canonicalName);
    }
    return canonicalName;
  }

  static String joinPackageAndSimpleName(String packageName, String simpleName) {
    if (simpleName == null || simpleName.isBlank()) {
      throw UnexpectedExceptions.withMessage("Class simple name should be not empty");
    }
    if (packageName == null || packageName.isEmpty()) {
      return simpleName;
    } else {
      return packageName + "." + simpleName;
    }
  }

  static String replaceSimpleName(String canonicalName, String newSimpleName) {
    if (canonicalName == null || canonicalName.isBlank()) {
      throw UnexpectedExceptions.withMessage("Class canonical name should be not empty");
    }
    if (newSimpleName == null || newSimpleName.isBlank()) {
      throw UnexpectedExceptions.withMessage("Class simple name should be not empty");
    }
    return joinPackageAndSimpleName(getPackageName(canonicalName), newSimpleName);
  }

  static String addPrefixToSimpleName(String prefix, String canonicalName) {
    String packageName = getPackageName(canonicalName);
    return packageName + (packageName.isEmpty() ? "" : ".") + prefix + getSimpleName(canonicalName);
  }
}
