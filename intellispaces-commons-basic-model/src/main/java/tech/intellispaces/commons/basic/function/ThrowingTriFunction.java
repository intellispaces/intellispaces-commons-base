package tech.intellispaces.commons.basic.function;

/**
 * Function that accepts three arguments and can throw exception.
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 * @param <T3> the third argument type.
 * @param <R> the function result type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowingTriFunction<T1, T2, T3, R, E extends Exception> {

  /**
   * Applies function.
   *
   * @param t1 the first argument.
   * @param t2 the second argument.
   * @param t3 the third argument.
   * @return the function result.
   * @throws E the exception type.
   */
  R applyThrows(T1 t1, T2 t2, T3 t3) throws E;
}
