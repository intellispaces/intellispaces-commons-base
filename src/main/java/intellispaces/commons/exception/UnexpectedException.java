package intellispacesframework.commons.exception;

import intellispacesframework.commons.utils.Strings;

/**
 * Unexpected exception.<p/>
 *
 * Unexpected exceptions are related to any unexpected failures in established code.
 */
public class UnexpectedException extends RuntimeException {

  public UnexpectedException() {
  }

  public UnexpectedException(Throwable cause) {
    super(cause);
  }

  public UnexpectedException(String message, Object... msgArguments) {
    super(Strings.format(message, msgArguments));
  }

  public UnexpectedException(Throwable cause, String message, Object... msgArguments) {
    super(Strings.format(message, msgArguments), cause);
  }
}
