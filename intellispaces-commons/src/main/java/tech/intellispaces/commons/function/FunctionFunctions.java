package tech.intellispaces.commons.function;

import tech.intellispaces.commons.exception.CoveredCheckedException;

import java.util.function.Function;

/**
 * Functions to operate with functions.
 */
public interface FunctionFunctions {

  static <T, R, E extends Throwable> R applyAndCoverIfChecked(
      ThrowableFunction<T, R, E> function, T t
  ) {
    try {
      return function.apply(t);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, R, E extends Throwable> R applyAndCoverIfChecked(
      ThrowableBiFunction<T1, T2, R, E> function, T1 t1, T2 t2
  ) {
    try {
      return function.apply(t1, t2);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, T3, R, E extends Throwable> R applyAndCoverIfChecked(
      ThrowableTriFunction<T1, T2, T3, R, E> function, T1 t1, T2 t2, T3 t3
  ) {
    try {
      return function.apply(t1, t2, t3);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  static <T1, T2, T3, T4, R, E extends Throwable> R applyAndCoverIfChecked(
      ThrowableQuadFunction<T1, T2, T3, T4, R, E> function, T1 t1, T2 t2, T3 t3, T4 t4
  ) {
    try {
      return function.apply(t1, t2, t3, t4);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredCheckedException(e);
    }
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Throwable> R applyAndUncoverIfCovered(
      Function<T, R> function, T argument, Class<E> e
  ) throws E {
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
