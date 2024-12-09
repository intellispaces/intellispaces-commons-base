package tech.intellispaces.general.exception;

/**
 * The checked exception.
 * <p>
 * This is the parent class for any exceptions related to possible and expected violations of any assumptions
 * imposed on a particular method.
 */
public class CheckedException extends Exception {

  public CheckedException() {}

  public CheckedException(String message) {
    super(message);
  }

  public CheckedException(Exception cause) {
    super(cause);
  }

  public CheckedException(String message, Exception cause) {
    super(message, cause);
  }
}
