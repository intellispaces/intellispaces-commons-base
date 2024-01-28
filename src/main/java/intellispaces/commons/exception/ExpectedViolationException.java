package intellispaces.commons.exception;

import intellispaces.commons.StringFunctions;

/**
 * Expected violation exception.<p/>
 *
 * This is the parent class for any exceptions related to <b>expected violations of assumptions</b> imposed on a particular method.<p/>
 *
 * Typical sample of expected exception are any business exceptions.
 */
public class ExpectedViolationException extends Exception {

  public ExpectedViolationException() {
  }

  public ExpectedViolationException(Throwable cause) {
    super(cause);
  }

  public ExpectedViolationException(String messageTemplate, Object... arguments) {
    super(StringFunctions.format(messageTemplate, arguments));
  }

  public ExpectedViolationException(Throwable cause, String messageTemplate, Object... arguments) {
    super(StringFunctions.format(messageTemplate, arguments), cause);
  }
}
