package intellispaces.common.base.function;

import intellispaces.common.base.exception.WrappedException;

import java.util.function.Function;

/**
 * Function provider.
 */
public interface Functions {

  static <T, R, E extends Exception> Function<T, R> wrapThrowingFunction(
      ThrowingFunction<T, R, E> function
  ) {
    return t -> {
      try {
        return function.applyThrows(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new WrappedException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Exception, E2 extends RuntimeException> Function<T, R> wrapThrowingFunction(
      ThrowingFunction<T, R, E> function, Function<E, E2> exceptionFactory
  ) {
    return t -> {
      try {
        return function.applyThrows(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw exceptionFactory.apply((E) e);
      }
    };
  }
}
