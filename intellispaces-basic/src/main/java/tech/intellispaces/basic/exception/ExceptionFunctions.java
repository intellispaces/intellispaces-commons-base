package tech.intellispaces.basic.exception;

/**
 * Exception processing functions.
 */
public interface ExceptionFunctions {

  static RuntimeException wrapIfChecked(Exception e) {
    if (e instanceof RuntimeException) {
      return (RuntimeException) e;
    } else {
      return new WrappedException(e);
    }
  }
}
