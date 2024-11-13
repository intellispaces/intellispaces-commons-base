package tech.intellispaces.common.entities.exception;

/**
 * Assumption violation exception.</p>
 *
 * This is the parent class for any exceptions related to <b>expected violations of any assumptions</b>
 * imposed on a particular method.</p>
 */
public class AssumptionViolationException extends Exception {

  public AssumptionViolationException() {}

  public AssumptionViolationException(String message) {
    super(message);
  }

  public AssumptionViolationException(Exception cause) {
    super(cause);
  }

  public AssumptionViolationException(String message, Exception cause) {
    super(message, cause);
  }
}
