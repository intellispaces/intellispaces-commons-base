package tech.intellispaces.commons.exception;

import tech.intellispaces.commons.text.StringFunctions;

import java.text.MessageFormat;

/**
 * Provider of the exception {@link CheckedException}.
 */
public interface CheckedExceptions {

  static CheckedException withCause(Exception cause) {
    return new CheckedException(null, cause);
  }

  static CheckedException withMessage(String message) {
    return new CheckedException(message);
  }

  static CheckedException withCauseAndMessage(Exception cause, String message) {
    return new CheckedException(message, cause);
  }

  static CheckedException withMessage(String template, Object... params) {
    return new CheckedException(StringFunctions.resolveTemplate(template, params));
  }

  static CheckedException withCauseAndMessage(
      Exception cause, String template, Object... params
  ) {
    return new CheckedException(StringFunctions.resolveTemplate(template, params), cause);
  }

  static CheckedException withFormattedMessage(String template, Object... params) {
    return new CheckedException(MessageFormat.format(template, params));
  }

  static CheckedException withCauseAndFormattedMessage(
      Exception cause, String template, Object... params
  ) {
    return new CheckedException(MessageFormat.format(template, params), cause);
  }
}
