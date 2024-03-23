package intellispaces.commons.function;

/**
 * Character processing functions.
 */
public interface CharFunctions {

  static boolean isBlankChar(char ch) {
    return ch == ' ' ||ch == '\t';
  }
}
