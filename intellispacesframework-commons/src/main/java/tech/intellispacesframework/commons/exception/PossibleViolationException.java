package tech.intellispacesframework.commons.exception;

import tech.intellispacesframework.commons.string.StringFunctions;

/**
 * Possible violation exception.
 *
 * <p>This is the parent class for any exceptions related to <b>possible and expected violations of assumptions</b> imposed on a particular method.
 *
 * <p>Typical sample of expected exception are any business exceptions.
 */
public class PossibleViolationException extends Exception {

  public PossibleViolationException() {
  }

  protected PossibleViolationException(String messageTemplate, Object... messageParams) {
    super(StringFunctions.format(messageTemplate, messageParams), null);
  }

  protected PossibleViolationException(Throwable cause, String messageTemplate, Object... messageParams) {
    super(StringFunctions.format(messageTemplate, messageParams), cause);
  }

  public static PossibleViolationException withCause(Throwable cause) {
    return new PossibleViolationException(cause, null);
  }

  public static PossibleViolationException withMessage(String messageTemplate, Object... messageParams) {
    return new PossibleViolationException(null, messageTemplate, messageParams);
  }

  public static PossibleViolationException withCauseAndMessage(Throwable cause, String messageTemplate, Object... messageParams) {
    return new PossibleViolationException(cause, messageTemplate, messageParams);
  }
}
