package tech.intellispaces.commons.function;

/**
 * Function that accepts three arguments and can throw exception.
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 * @param <R> a function result type.
 * @param <E> an exception type.
 */
@FunctionalInterface
public interface ThrowingFunction3<T1, T2, T3, R, E extends Exception> {

  /**
   * Applies function.
   *
   * @param t1 a first argument.
   * @param t2 a second argument.
   * @param t3 a third argument.
   * @return a function result.
   * @throws E an exception type.
   */
  R applyThrows(T1 t1, T2 t2, T3 t3) throws E;
}
