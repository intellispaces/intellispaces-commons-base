package intellispaces.commons;

import intellispaces.commons.exception.CoveredException;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Exception processing functions.
 */
public interface ExceptionFunctions {

  static <T, R, E extends Exception> Function<T, R> coverThrowableFunction(ThrowingFunction<T, R, E> function) {
    return t -> {
      try {
        return function.apply(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new CoveredException(e);
      }
    };
  }

  static <T, E extends Exception> Consumer<T> coverThrowableConsumer(ThrowingConsumer<T, E> consumer) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new CoveredException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <E extends Exception> void uncoverThrowable(Class<E> e, Runnable runnable) throws E {
    try {
      runnable.run();
    } catch (CoveredException se) {
      if (e.isInstance(se.getCause())) {
        throw (E) se.getCause();
      } else {
        throw se;
      }
    }
  }
}
