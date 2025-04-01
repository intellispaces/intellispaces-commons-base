package tech.intellispaces.commons.function;

/**
 * Function that accepts four arguments.
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 * @param <T4> a fourth argument type.
 * @param <R> a function result type.
 */
@FunctionalInterface
public interface Function4<T1, T2, T3, T4, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @param arg4 the fourth argument.
   * @return the function result.
   */
  R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
}
