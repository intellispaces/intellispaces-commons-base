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
}
