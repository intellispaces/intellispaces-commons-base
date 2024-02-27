package intellispaces.commons.exception;

import intellispaces.commons.StringFunctions;

/**
 * Possible violation exception.<p/>
 *
 * This is the parent class for any exceptions related to <b>possible violations of assumptions</b> imposed on a particular method.<p/>
 *
 * Typical sample of expected exception are any business exceptions.
 */
public class PossibleViolationException extends Exception {

  public PossibleViolationException() {
  }

  public PossibleViolationException(Throwable cause) {
    super(cause);
  }

  public PossibleViolationException(String messageTemplate, Object... arguments) {
    super(StringFunctions.format(messageTemplate, arguments));
  }

  public PossibleViolationException(Throwable cause, String messageTemplate, Object... arguments) {
    super(StringFunctions.format(messageTemplate, arguments), cause);
  }
}
