package tech.intellispacesframework.commons.exception;

/**
 * The unchecked exception covering the other checked exception.
 */
public class CoveredCheckedException extends RuntimeException {

  public CoveredCheckedException(Exception cause) {
    super(cause);
  }

  public static CoveredCheckedException withCause(Exception cause) {
    return new CoveredCheckedException(cause);
  }
}
