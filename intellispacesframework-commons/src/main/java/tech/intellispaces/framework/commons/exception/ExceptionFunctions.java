package tech.intellispaces.framework.commons.exception;

import tech.intellispaces.framework.commons.function.ThrowingBiFunction;
import tech.intellispaces.framework.commons.function.ThrowingConsumer;
import tech.intellispaces.framework.commons.function.ThrowingFunction;
import tech.intellispaces.framework.commons.function.ThrowingQuadFunction;
import tech.intellispaces.framework.commons.function.ThrowingTriFunction;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Exception processing functions.
 */
public interface ExceptionFunctions {

  static <T, R, E extends Throwable> Function<T, R> coverThrowableFunction(ThrowingFunction<T, R, E> function) {
    return t -> {
      try {
        return function.apply(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw new CoveredCheckedException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Throwable, E2 extends RuntimeException> Function<T, R> coverThrowableFunction(
      ThrowingFunction<T, R, E> function, Function<E, E2> exceptionFactory
  ) {
    return t -> {
      try {
        return function.apply(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw exceptionFactory.apply((E) e);
      }
    };
  }

  static <T, E extends Throwable> Consumer<T> coverThrowableConsumer(ThrowingConsumer<T, E> consumer) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw new CoveredCheckedException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <T, E extends Throwable, E2 extends RuntimeException> Consumer<T> coverThrowableConsumer(
      ThrowingConsumer<T, E> consumer, Function<E, E2> exceptionFactory
  ) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw exceptionFactory.apply((E) e);
      }
    };
  }

  static RuntimeException coverException(Throwable e) {
    if (e instanceof RuntimeException) {
      return (RuntimeException) e;
    } else {
      return new CoveredCheckedException(e);
    }
  }

  static <T, R, E extends Throwable> R coverException(ThrowingFunction<T, R, E> function, T t) {
    try {
      return function.apply(t);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, R, E extends Throwable> R coverException(ThrowingBiFunction<T1, T2, R, E> function, T1 t1, T2 t2) {
    try {
      return function.apply(t1, t2);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, T3, R, E extends Throwable> R coverException(ThrowingTriFunction<T1, T2, T3, R, E> function, T1 t1, T2 t2, T3 t3) {
    try {
      return function.apply(t1, t2, t3);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, T3, T4, R, E extends Throwable> R coverException(ThrowingQuadFunction<T1, T2, T3, T4, R, E> function, T1 t1, T2 t2, T3 t3, T4 t4) {
    try {
      return function.apply(t1, t2, t3, t4);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  @SuppressWarnings("unchecked")
  static <E extends Throwable> void uncoverThrowable(Class<E> e, Runnable runnable) throws E {
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
  static <T, R, E extends Throwable> R uncoverThrowable(Class<E> e, T argument, Function<T, R> function) throws E {
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
