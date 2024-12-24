package tech.intellispaces.general.text;

import tech.intellispaces.general.exception.UnexpectedExceptions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * String related functions.
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

  static String join(String first, String second, String separator) {
    if (first == null && second == null) {
      return null;
    }
    if (isNullOrBlank(first)) {
      return second;
    }
    if (isNullOrBlank(second)) {
      return first;
    }
    return first + separator + second;
  }

  static String join(String first, String second, String third, String separator) {
    if (first == null && second == null && third == null) {
      return null;
    }
    if (isNullOrBlank(first)) {
      return join(second, third, separator);
    }
    if (isNullOrBlank(second)) {
      return join(first, third, separator);
    }
    if (isNullOrBlank(third)) {
      return join(first, second, separator);
    }
    return first + separator + second + separator + third;
  }

  static List<String> splitAndTrim(String string, String separator) {
    if (string == null) {
      return null;
    }
    if (separator == null) {
      return List.of(string);
    }
    return Arrays.stream(string.split(separator))
        .map(String::trim)
        .toList();
  }

  static int numberSubstrings(String string, String subString) {
    if (string == null || subString == null) {
      return 0;
    }
    int count = 0;
    int lastIndex = 0;
    while (true) {
      lastIndex = string.indexOf(subString, lastIndex);
      if (lastIndex == -1) {
        return count;
      }
      count++;
      lastIndex += subString.length();
    }
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

  static String replaceTailOrElseThrow(String source, String tail, String replacement) {
    if (source == null) {
      throw UnexpectedExceptions.withMessage("Source string is null");
    }
    if (tail == null) {
      throw UnexpectedExceptions.withMessage("Substring is null");
    }
    if (replacement == null) {
      replacement = "";
    }

    int endingOffset = source.length() - tail.length();
    if (endingOffset < 0 || !tail.equals(source.substring(endingOffset))) {
      throw UnexpectedExceptions.withMessage("Source string '{0}' does not contain tail '{1}'",
          source, tail);
    }
    return source.substring(0, endingOffset) + replacement + source.substring(endingOffset + tail.length());
  }

  static String replaceSingleOrElseThrow(String source, String substring, String replacement) {
    if (source == null) {
      throw UnexpectedExceptions.withMessage("Source string is null");
    }
    if (substring == null) {
      throw UnexpectedExceptions.withMessage("Substring is null");
    }
    if (replacement == null) {
      replacement = "";
    }

    int numSubstrings = numberSubstrings(source, substring);
    if (numSubstrings == 0) {
      throw UnexpectedExceptions.withMessage("Source string '{0}' does not contain substring '{1}'",
          source, substring);
    } else if (numSubstrings > 1) {
      throw UnexpectedExceptions.withMessage("Source string '{0}' contains more than one substrings '{1}'",
          source, substring);
    }
    return source.replace(substring, replacement);
  }

  static String removeTailOrElseThrow(String source, String tail) {
    return replaceTailOrElseThrow(source, tail, "");
  }

  static String createBlankString(int length) {
    if (length <= 0) {
      return "";
    }
    char[] charArray = new char[length];
    Arrays.fill(charArray, ' ');
    return new String(charArray);
  }

  static InputStream stringToInputStream(String string) {
    if (string == null) {
      return null;
    }
    if (string.isEmpty()) {
      return InputStream.nullInputStream();
    }
    return new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
  }

  static String resolveTemplate(String template, Object... params) {
    if (template == null) {
      return null;
    }

    var sb = new StringBuilder();
    char[] chars = template.toCharArray();
    boolean openBrace = false;
    for (int ind = 0; ind < chars.length; ind++) {
      char ch = chars[ind];
      if (ch != '{') {
        sb.append(ch);
      } else {
        openBrace = true;
        int beginInd = ind;
        while (ind < chars.length) {
          ch = chars[ind];
          if (ch == '}') {
            openBrace = false;
            String value = template.substring(beginInd + 1, ind);
            try {
              int paramInd = Integer.parseInt(value);
              if (paramInd >= 0 && paramInd < params.length) {
                sb.append(params[paramInd]);
              } else {
                throw UnexpectedExceptions.withMessage(
                    "Could not resolve string template '{0}'. Parameter index {1} is out of range", template, value
                );
              }
            } catch (NumberFormatException e) {
              throw UnexpectedExceptions.withCauseAndMessage(
                  e, "Could not resolve string template '{0}'. Invalid parameter index '{1}'", template, value);
            }
            break;
          }
          ind++;
        }
      }
      if (openBrace) {
        throw UnexpectedExceptions.withMessage(
            "Could not resolve string template '{0}'. There is no paired closing curly brace", template
        );
      }
    }
    return sb.toString();
  }
}
