package intellispaces.common.base.exception;

/**
 * Provider of the exception {@link WrappedExceptions}.
 */
public interface WrappedExceptions {

  static WrappedException ofChecked(Exception checkedException) {
    return new WrappedException(checkedException);
  }
}
