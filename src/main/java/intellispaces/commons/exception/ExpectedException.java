package intellispaces.commons.exception;

import intellispaces.commons.util.Strings;

/**
 * Expected exception.<p/>
 *
 * This is the parent class for any exceptions related to <b>expected violations of assumptions</b> imposed on a particular method.<p/>
 *
 * Typical sample of expected exception are any business exceptions.
 */
public class ExpectedException extends Exception {

  public ExpectedException() {
  }

  public ExpectedException(Throwable cause) {
    super(cause);
  }

  public ExpectedException(String messageTemplate, Object... messageArguments) {
    super(Strings.format(messageTemplate, messageArguments));
  }

  public ExpectedException(Throwable cause, String messageTemplate, Object... messageArguments) {
    super(Strings.format(messageTemplate, messageArguments), cause);
  }
}
