package tech.intellispaces.commons.function;

/**
 * Function that accepts four arguments and can throw exception.
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 * @param <T4> a fourth argument type.
 * @param <R> a function result type.
 * @param <E> an exception type.
 */
@FunctionalInterface
public interface ThrowingFunction4<T1, T2, T3, T4, R, E extends Exception> {

  /**
   * Applies function.
   *
   * @param arg1 a first argument.
   * @param arg2 a second argument.
   * @param arg3 a third argument.
   * @param arg4 a fourth argument.
   * @return a function result.
   * @throws E an exception type.
   */
  R applyThrows(T1 arg1, T2 arg2, T3 arg3, T4 arg4) throws E;
}
