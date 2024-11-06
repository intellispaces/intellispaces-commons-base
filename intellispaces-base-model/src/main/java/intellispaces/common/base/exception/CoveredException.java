package intellispaces.common.base.exception;

/**
 * The unchecked exception covering the checked exception.
 */
public class CoveredException extends RuntimeException {

  public CoveredException(Throwable cause) {
    super(cause);
  }

  public static CoveredException withCause(Throwable cause) {
    return new CoveredException(cause);
  }
}
