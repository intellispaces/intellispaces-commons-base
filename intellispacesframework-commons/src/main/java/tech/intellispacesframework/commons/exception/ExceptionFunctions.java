package tech.intellispacesframework.commons.exception;

import tech.intellispacesframework.commons.function.ThrowingBiFunction;
import tech.intellispacesframework.commons.function.ThrowingConsumer;
import tech.intellispacesframework.commons.function.ThrowingFunction;
import tech.intellispacesframework.commons.function.ThrowingQuadFunction;
import tech.intellispacesframework.commons.function.ThrowingTriFunction;

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

  static <T, R, E extends Exception> R coverException(ThrowingFunction<T, R, E> function, T t) {
    try {
      return function.apply(t);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, R, E extends Exception> R coverException(ThrowingBiFunction<T1, T2, R, E> function, T1 t1, T2 t2) {
    try {
      return function.apply(t1, t2);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, T3, R, E extends Exception> R coverException(ThrowingTriFunction<T1, T2, T3, R, E> function, T1 t1, T2 t2, T3 t3) {
    try {
      return function.apply(t1, t2, t3);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, T3, T4, R, E extends Exception> R coverException(ThrowingQuadFunction<T1, T2, T3, T4, R, E> function, T1 t1, T2 t2, T3 t3, T4 t4) {
    try {
      return function.apply(t1, t2, t3, t4);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new CoveredCheckedException(e);
    }
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
