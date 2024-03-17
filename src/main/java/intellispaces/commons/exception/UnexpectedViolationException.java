package intellispaces.commons.exception;

import intellispaces.commons.StringFunctions;

/**
 * Unexpected violation exception.
 *
 * <p>This is the parent class for any exceptions related to <b>unexpected violations of assumptions</b> imposed on a particular method.
 */
public class UnexpectedViolationException extends RuntimeException {

  public UnexpectedViolationException() {
  }

  public UnexpectedViolationException(Throwable cause) {
    super(cause);
  }

  public UnexpectedViolationException(String messageTemplate, Object... arguments) {
    super(StringFunctions.format(messageTemplate, arguments));
  }

  public UnexpectedViolationException(Throwable cause, String messageTemplate, Object... arguments) {
    super(StringFunctions.format(messageTemplate, arguments), cause);
  }
}
