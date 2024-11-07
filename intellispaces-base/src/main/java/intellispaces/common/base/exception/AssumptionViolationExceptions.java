package intellispaces.common.base.exception;

import intellispaces.common.base.text.StringFunctions;

import java.text.MessageFormat;

/**
 * Exception provider.
 */
public interface AssumptionViolationExceptions {

  static AssumptionViolationException withCause(Throwable cause) {
    return new AssumptionViolationException(null, cause);
  }

  static AssumptionViolationException withMessage(String message) {
    return new AssumptionViolationException(message);
  }

  static AssumptionViolationException withCauseAndMessage(Throwable cause, String message) {
    return new AssumptionViolationException(message, cause);
  }

  static AssumptionViolationException withMessage(String template, Object... params) {
    return new AssumptionViolationException(StringFunctions.resolveTemplate(template, params));
  }

  static AssumptionViolationException withCauseAndMessage(
      Throwable cause, String template, Object... params
  ) {
    return new AssumptionViolationException(StringFunctions.resolveTemplate(template, params), cause);
  }

  static AssumptionViolationException withFormattedMessage(String template, Object... params) {
    return new AssumptionViolationException(MessageFormat.format(template, params));
  }

  static AssumptionViolationException withCauseAndFormattedMessage(
      Throwable cause, String template, Object... params
  ) {
    return new AssumptionViolationException(MessageFormat.format(template, params), cause);
  }
}
