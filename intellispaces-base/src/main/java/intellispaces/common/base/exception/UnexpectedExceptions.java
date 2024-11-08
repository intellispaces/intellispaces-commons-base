package intellispaces.common.base.exception;

import intellispaces.common.base.text.StringFunctions;

import java.text.MessageFormat;

/**
 * Provider of the exception {@link UnexpectedException}.
 */
public interface UnexpectedExceptions {

  static UnexpectedException withCause(Throwable cause) {
    return new UnexpectedException(null, cause);
  }

  static UnexpectedException withMessage(String message) {
    return new UnexpectedException(message);
  }

  static UnexpectedException withCauseAndMessage(Throwable cause, String message) {
    return new UnexpectedException(message, cause);
  }

  static UnexpectedException withMessage(String template, Object... params) {
    return new UnexpectedException(StringFunctions.resolveTemplate(template, params));
  }

  static UnexpectedException withCauseAndMessage(
      Throwable cause, String template, Object... params
  ) {
    return new UnexpectedException(StringFunctions.resolveTemplate(template, params), cause);
  }

  static UnexpectedException withFormattedMessage(String template, Object... params) {
    return new UnexpectedException(MessageFormat.format(template, params));
  }

  static UnexpectedException withCauseAndFormattedMessage(
      Throwable cause, String template, Object... params
  ) {
    return new UnexpectedException(MessageFormat.format(template, params), cause);
  }
}
