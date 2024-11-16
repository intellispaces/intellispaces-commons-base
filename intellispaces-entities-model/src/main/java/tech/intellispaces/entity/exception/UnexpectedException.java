package tech.intellispaces.entity.exception;

/**
 * Unexpected assumption violation exception.<p/>
 *
 * This is the parent class for any exceptions related to violations of assumptions that are not expected to be violated
 * on a particular method.
 */
public class UnexpectedException extends RuntimeException {

  public UnexpectedException(String message) {
    super(message);
  }

  public UnexpectedException(Throwable cause) {
    super(cause);
  }

  public UnexpectedException(String message, Throwable cause) {
    super(message, cause);
  }
}
