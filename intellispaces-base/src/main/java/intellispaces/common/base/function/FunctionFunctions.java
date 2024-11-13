package intellispaces.common.base.function;

import intellispaces.common.base.exception.WrappedException;

import java.util.function.Function;

/**
 * Function related processed functions.
 */
public interface FunctionFunctions {

  static <T, R, E extends Exception> R applyAndWrap(
      T value, ThrowingFunction<T, R, E> function
  ) {
    try {
      return function.applyThrows(value);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new WrappedException(e);
    }
  }

  static <T1, T2, R, E extends Exception> R applyAndWrap(
      T1 value1, T2 value2, ThrowingBiFunction<T1, T2, R, E> function
  ) {
    try {
      return function.applyThrows(value1, value2);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new WrappedException(e);
    }
  }

  static <T1, T2, T3, R, E extends Exception> R applyAndWrap(
      T1 value1, T2 value2, T3 value3, ThrowingTriFunction<T1, T2, T3, R, E> function
  ) {
    try {
      return function.applyThrows(value1, value2, value3);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new WrappedException(e);
    }
  }

  static <T1, T2, T3, T4, R, E extends Exception> R applyAndWrap(
      T1 value1, T2 value2, T3 value3, T4 value4, ThrowingQuadFunction<T1, T2, T3, T4, R, E> function
  ) {
    try {
      return function.applyThrows(value1, value2, value3, value4);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new WrappedException(e);
    }
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Exception> R applyAndUnwrap(
      T value, Function<T, R> function, Class<E> e
  ) throws E {
    try {
      return function.apply(value);
    } catch (WrappedException se) {
      if (e.isInstance(se.getCause())) {
        throw (E) se.getCause();
      } else {
        throw se;
      }
    }
  }

  @SuppressWarnings("unchecked")
  static <E extends Exception> void runAndUnwrap(Runnable runnable, Class<E> e) throws E {
    try {
      runnable.run();
    } catch (WrappedException ce) {
      if (e.isInstance(ce.getCause())) {
        throw (E) ce.getCause();
      } else {
        throw ce;
      }
    }
  }
}
