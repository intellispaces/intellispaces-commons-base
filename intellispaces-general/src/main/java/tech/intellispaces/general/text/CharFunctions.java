package tech.intellispaces.general.text;

/**
 * Character related functions.
 */
public interface CharFunctions {

  /**
   * Tests character is gap or not. A gap character means a space or tab.
   *
   * @param ch the character.
   * @return <code>true</code> if character is gap or <code>false</code> otherwise.
   */
  static boolean isGapChar(char ch) {
    return ch == ' ' || ch == '\t';
  }
}
