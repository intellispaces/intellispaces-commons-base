package intellispaces.commons.function;

import intellispaces.commons.exception.CoveredCheckedException;

import java.util.function.Function;

public interface Functions {

  static <T, R, E extends Throwable> Function<T, R> coveredThrowableFunction(
      ThrowableFunction<T, R, E> function
  ) {
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
  static <T, R, E extends Throwable, E2 extends RuntimeException> Function<T, R> coveredThrowableFunction(
      ThrowableFunction<T, R, E> function, Function<E, E2> exceptionFactory
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
}
