package intellispaces.common.base.exception;

/**
 * The unchecked exception that wrapped the checked exception.
 */
public class WrappedException extends RuntimeException {

  public WrappedException(Throwable cause) {
    super(null, cause);
  }
}
