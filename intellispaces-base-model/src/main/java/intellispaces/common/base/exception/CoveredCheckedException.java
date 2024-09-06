package intellispaces.common.base.exception;

/**
 * The unchecked exception covering the other checked exception.
 */
public class CoveredCheckedException extends RuntimeException {

  public CoveredCheckedException(Throwable cause) {
    super(cause);
  }

  public static CoveredCheckedException withCause(Throwable cause) {
    return new CoveredCheckedException(cause);
  }
}
