package intellispaces.common.base.text;

import intellispaces.common.base.exception.UnexpectedViolationException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Strings processing functions.
 */
public interface TextFunctions {

  /**
   * Tests character is gap or not. A gap character means a space or tab.
   *
   * @param ch the character.
   * @return <code>true</code> if character is gap or <code>false</code> otherwise.
   */
  static boolean isGapChar(char ch) {
    return ch == ' ' || ch == '\t';
  }

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

  static String replaceTailOrElseThrow(String source, String substring, String replacement) {
    if (source == null) {
      throw UnexpectedViolationException.withMessage("Source string is null");
    }
    if (substring == null) {
      throw UnexpectedViolationException.withMessage("Substring is null");
    }
    if (replacement == null) {
      replacement = "";
    }

    int endingOffset = source.length() - substring.length();
    if (endingOffset < 0 || !substring.equals(source.substring(endingOffset))) {
      throw UnexpectedViolationException.withMessage("Source string ''{0}'' does not contain tail substring ''{1}''",
          source, substring);
    }
    return source.substring(0, endingOffset) + replacement + source.substring(endingOffset + substring.length());
  }

  static String replaceSingleOrElseThrow(String source, String substring, String replacement) {
    if (source == null) {
      throw UnexpectedViolationException.withMessage("Source string is null");
    }
    if (substring == null) {
      throw UnexpectedViolationException.withMessage("Substring is null");
    }
    if (replacement == null) {
      replacement = "";
    }

    int numSubstrings = numberSubstrings(source, substring);
    if (numSubstrings == 0) {
      throw UnexpectedViolationException.withMessage("Source string ''{0}'' does not contain substring ''{1}''",
          source, substring);
    } else if (numSubstrings > 1) {
      throw UnexpectedViolationException.withMessage("Source string ''{0}'' contains more than one substrings ''{1}''",
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
}
