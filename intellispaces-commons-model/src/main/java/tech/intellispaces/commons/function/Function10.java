package tech.intellispaces.commons.function;

/**
 * Function that accepts ten arguments.
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 * @param <T4> a fourth argument type.
 * @param <T5> a fifth argument type.
 * @param <T6> a sixth argument type.
 * @param <T7> a seventh argument type.
 * @param <T8> an eighth argument type.
 * @param <T9> a ninth argument type.
 * @param <T10> a tenth argument type.
 * @param <R> a function result type.
 */
@FunctionalInterface
public interface Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> {

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
   * @param arg9 a ninth argument.
   * @param arg10 a tenth argument.
   * @return a function result.
   */
  R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6, T7 arg7, T8 arg8, T9 arg9, T10 arg10);
}
