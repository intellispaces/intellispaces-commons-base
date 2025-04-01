package tech.intellispaces.commons.function;

/**
 * Function that accepts eight arguments.
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 * @param <T4> a fourth argument type.
 * @param <T5> a fifth argument type.
 * @param <T6> a sixth argument type.
 * @param <T7> a seventh argument type.
 * @param <T8> an eighth argument type.
 * @param <R> a function result type.
 */
@FunctionalInterface
public interface Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> {

  /**
   * Applies function.
   *
   * @param arg1 a first argument.
   * @param arg2 a second argument.
   * @param arg3 a third argument.
   * @param arg4 a fourth argument.
   * @param arg5 a fifth argument.
   * @param arg6 a sixth argument.
   * @param arg7 a seventh argument.
   * @param arg8 an eighth argument.
   * @return a function result.
   */
  R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8);
}
