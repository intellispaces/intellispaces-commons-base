package intellispaces.common.base.function;

import intellispaces.common.base.exception.CoveredException;

import java.util.function.Function;

/**
 * Function provider.
 */
public interface Functions {

  static <T, R, E extends Throwable> Function<T, R> coveredFunction(
      ThrowingFunction<T, R, E> function
  ) {
    return t -> {
      try {
        return function.applyThrows(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw new CoveredException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Throwable, E2 extends RuntimeException> Function<T, R> coveredFunction(
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
