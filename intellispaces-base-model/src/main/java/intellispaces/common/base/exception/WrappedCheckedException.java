package intellispaces.common.base.exception;

/**
 * The unchecked exception wrapped the checked exception.
 */
public class WrappedCheckedException extends RuntimeException {

  public WrappedCheckedException(Throwable cause) {
    super(null, cause);
  }
}
