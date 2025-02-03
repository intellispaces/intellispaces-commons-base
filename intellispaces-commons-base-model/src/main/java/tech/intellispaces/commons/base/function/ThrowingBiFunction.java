package tech.intellispaces.commons.base.function;

/**
 * Function that accepts two arguments and can throw exception.
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 * @param <R> the function result type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowingBiFunction<T1, T2, R, E extends Exception> {

  /**
   * Applies function.
   *
   * @param t1 the first argument.
   * @param t2 the second argument.
   * @return the function result.
   * @throws E the exception type.
   */
  R applyThrows(T1 t1, T2 t2) throws E;
}