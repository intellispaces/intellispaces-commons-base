package tech.intellispaces.entity.exception;

/**
 * The unchecked exception that wrapped the checked exception.
 */
public class WrappedException extends RuntimeException {

  public WrappedException(Exception checkedException) {
    super("Wrapped checked exception", checkedException);
  }
}
