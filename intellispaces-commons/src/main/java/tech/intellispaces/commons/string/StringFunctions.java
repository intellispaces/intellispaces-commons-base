package tech.intellispaces.commons.string;

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

  static String format(String template, Object... arguments) {
    if (template == null) {
      return null;
    }
    var sb = new StringBuilder();
    char[] chars = template.toCharArray();
    int indexArg = 0;
    for (int index = 0; index < chars.length; index++) {
      if (chars[index] == '{' && index < chars.length - 1 && chars[index+1] == '}') {
        if (arguments == null) {
          sb.append("null");
        } else if (indexArg < arguments.length) {
          if (arguments[indexArg] != null) {
            sb.append(arguments[indexArg]);
            indexArg++;
          } else {
            sb.append("null");
          }
        } else {
          sb.append("null");
        }
        index++;
      } else {
        sb.append(chars[index]);
      }
    }
    return sb.toString();
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

  static String createBlankString(int length) {
    if (length <= 0) {
      return "";
    }
    char[] charArray = new char[length];
    Arrays.fill(charArray, ' ');
    return new String(charArray);
  }
}
