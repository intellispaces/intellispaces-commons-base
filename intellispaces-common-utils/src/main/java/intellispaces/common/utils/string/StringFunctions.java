package intellispaces.common.utils.string;

import intellispaces.common.utils.exception.UnexpectedViolationException;

import java.util.Arrays;

/**
 * Strings processing functions.
 */
public interface StringFunctions {

  static boolean isNullOrBlank(String string) {
    return string == null || string.isBlank();
  }

  static boolean isNotBlank(String string) {
    return !isNullOrBlank(string);
  }

  static String capitalizeFirstLetter(String string) {
    if (string == null || string.isEmpty()) {
      return string;
    }
    return string.substring(0, 1).toUpperCase() + string.substring(1);
  }

  static String lowercaseFirstLetter(String string) {
    if (string == null || string.isEmpty()) {
      return string;
    }
    return string.substring(0, 1).toLowerCase() + string.substring(1);
  }

  static String replaceLast(String string, String target, String replacement) {
    if (string == null) {
      return null;
    }
    if (target == null) {
      return string;
    }
    if (replacement == null) {
      replacement = "";
    }
    int index = string.lastIndexOf(target);
    if (index > -1) {
      return string.substring(0, index) + replacement + string.substring(index + target.length());
    } else {
      return string;
    }
  }

  static String replaceEndingOrElseThrow(String string, String ending, String replacement) {
    if (string == null) {
      throw UnexpectedViolationException.withMessage("Source string is null");
    }
    if (ending == null) {
      throw UnexpectedViolationException.withMessage("Ending string is null");
    }
    if (replacement == null) {
      replacement = "";
    }

    int endingOffset = string.length() - ending.length();
    if (endingOffset < 0 || !ending.equals(string.substring(endingOffset))) {
      throw UnexpectedViolationException.withMessage("Source string does not contain ending string");
    }
    return string.substring(0, endingOffset) + replacement + string.substring(endingOffset + ending.length());
  }

  static String createBlankString(int length) {
    if (length <= 0) {
      return "";
    }
    char[] charArray = new char[length];
    Arrays.fill(charArray, ' ');
    return new String(charArray);
  }
}
