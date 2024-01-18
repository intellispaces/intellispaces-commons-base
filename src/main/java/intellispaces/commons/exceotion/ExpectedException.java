package intellispacesframework.commons.exception;

import intellispacesframework.commons.utils.Strings;

/**
 * Declared exception.<p/>
 *
 * Declared exceptions are caused by any expected assumption violation in established code and can be specific handled.
 */
public class ExpectedException extends Exception {

  public ExpectedException() {
  }

  public ExpectedException(Throwable cause) {
    super(cause);
  }

  public ExpectedException(String message, Object... msgArguments) {
    super(Strings.format(message, msgArguments));
  }

  public ExpectedException(Throwable cause, String message, Object... msgArguments) {
    super(Strings.format(message, msgArguments), cause);
  }
}
