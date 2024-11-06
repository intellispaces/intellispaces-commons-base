package intellispaces.common.base.function;

import intellispaces.common.base.exception.CoveredException;

import java.util.function.Function;

/**
 * Function related processed functions.
 */
public interface FunctionFunctions {

  static <T, R, E extends Throwable> R applyAndCover(
      ThrowingFunction<T, R, E> function, T t
  ) {
    try {
      return function.applyThrows(t);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredException(e);
    }
  }

  static <T1, T2, R, E extends Throwable> R applyAndCover(
      ThrowingBiFunction<T1, T2, R, E> function, T1 t1, T2 t2
  ) {
    try {
      return function.applyThrows(t1, t2);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredException(e);
    }
  }

  static <T1, T2, T3, R, E extends Throwable> R applyAndCover(
      ThrowingTriFunction<T1, T2, T3, R, E> function, T1 t1, T2 t2, T3 t3
  ) {
    try {
      return function.applyThrows(t1, t2, t3);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredException(e);
    }
  }

  static <T1, T2, T3, T4, R, E extends Throwable> R applyAndCover(
      ThrowingQuadFunction<T1, T2, T3, T4, R, E> function, T1 t1, T2 t2, T3 t3, T4 t4
  ) {
    try {
      return function.applyThrows(t1, t2, t3, t4);
    } catch (RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new CoveredException(e);
    }
  }

  @SuppressWarnings("unchecked")
  static <T, R, E extends Throwable> R applyAndUncover(
      Function<T, R> function, T argument, Class<E> e
  ) throws E {
    try {
      return function.apply(argument);
    } catch (CoveredException se) {
      if (e.isInstance(se.getCause())) {
        throw (E) se.getCause();
      } else {
        throw se;
      }
    }
  }

  @SuppressWarnings("unchecked")
  static <E extends Throwable> void runAndUncover(Runnable runnable, Class<E> e) throws E {
    try {
      runnable.run();
    } catch (CoveredException ce) {
      if (e.isInstance(ce.getCause())) {
        throw (E) ce.getCause();
      } else {
        throw ce;
      }
    }
  }
}
