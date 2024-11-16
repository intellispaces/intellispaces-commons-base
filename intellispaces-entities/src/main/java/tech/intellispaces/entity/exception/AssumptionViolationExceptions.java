package tech.intellispaces.entity.exception;

import tech.intellispaces.entity.text.StringFunctions;

import java.text.MessageFormat;

/**
 * Provider of the exception {@link AssumptionViolationException}.
 */
public interface AssumptionViolationExceptions {

  static AssumptionViolationException withCause(Exception cause) {
    return new AssumptionViolationException(null, cause);
  }

  static AssumptionViolationException withMessage(String message) {
    return new AssumptionViolationException(message);
  }

  static AssumptionViolationException withCauseAndMessage(Exception cause, String message) {
    return new AssumptionViolationException(message, cause);
  }

  static AssumptionViolationException withMessage(String template, Object... params) {
    return new AssumptionViolationException(StringFunctions.resolveTemplate(template, params));
  }

  static AssumptionViolationException withCauseAndMessage(
      Exception cause, String template, Object... params
  ) {
    return new AssumptionViolationException(StringFunctions.resolveTemplate(template, params), cause);
  }

  static AssumptionViolationException withFormattedMessage(String template, Object... params) {
    return new AssumptionViolationException(MessageFormat.format(template, params));
  }

  static AssumptionViolationException withCauseAndFormattedMessage(
      Exception cause, String template, Object... params
  ) {
    return new AssumptionViolationException(MessageFormat.format(template, params), cause);
  }
}
