package intellispaces.commons.function;

import intellispaces.commons.exception.CoveredCheckedException;
import intellispaces.commons.model.function.ThrowingConsumer;
import intellispaces.commons.model.function.ThrowingFunction;

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
        throw new CoveredCheckedException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Exception, E2 extends RuntimeException> Function<T, R> coverThrowableFunction(
      ThrowingFunction<T, R, E> function, Function<E, E2> exceptionFactory
  ) {
    return t -> {
      try {
        return function.apply(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw exceptionFactory.apply((E) e);
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
        throw new CoveredCheckedException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <T, E extends Exception, E2 extends RuntimeException> Consumer<T> coverThrowableConsumer(
      ThrowingConsumer<T, E> consumer, Function<E, E2> exceptionFactory
  ) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw exceptionFactory.apply((E) e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <E extends Exception> void uncoverThrowable(Class<E> e, Runnable runnable) throws E {
    try {
      runnable.run();
    } catch (CoveredCheckedException se) {
      if (e.isInstance(se.getCause())) {
        throw (E) se.getCause();
      } else {
        throw se;
      }
    }
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Exception> R uncoverThrowable(Class<E> e, T argument, Function<T, R> function) throws E {
    try {
      return function.apply(argument);
    } catch (CoveredCheckedException se) {
      if (e.isInstance(se.getCause())) {
        throw (E) se.getCause();
      } else {
        throw se;
      }
    }
  }
}
