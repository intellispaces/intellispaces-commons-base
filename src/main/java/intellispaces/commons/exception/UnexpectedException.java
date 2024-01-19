package intellispaces.commons.exception;

import intellispaces.commons.util.Strings;

/**
 * Unexpected exception.<p/>
 *
 * This is the parent class for any exceptions related to <b>unexpected violations of assumptions</b> imposed on a particular method.
 */
public class UnexpectedException extends RuntimeException {

  public UnexpectedException() {
  }

  public UnexpectedException(Throwable cause) {
    super(cause);
  }

  public UnexpectedException(String messageTemplate, Object... messageArguments) {
    super(Strings.format(messageTemplate, messageArguments));
  }

  public UnexpectedException(Throwable cause, String messageTemplate, Object... messageArguments) {
    super(Strings.format(messageTemplate, messageArguments), cause);
  }
}
