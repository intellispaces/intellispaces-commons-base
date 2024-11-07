package intellispaces.common.base.exception;

/**
 * Exception provider.
 */
public interface WrappedCheckedExceptions {

  static WrappedCheckedException withCause(Throwable cause) {
    return new WrappedCheckedException(cause);
  }
}
