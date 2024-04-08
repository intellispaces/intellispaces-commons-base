package intellispaces.commons.function;

/**
 * Character processing functions.
 */
public interface CharFunctions {

  /**
   * Tests character is blank or not. A blank character means a space or tab.
   *
   * @param ch the character.
   * @return <code>true</code> if character is blank or <code>false</code> otherwise.
   */
  static boolean isBlankChar(char ch) {
    return ch == ' ' ||ch == '\t';
  }
}
