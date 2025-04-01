package tech.intellispaces.commons.function;

/**
 * Consumer that accepts six arguments.
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 * @param <T4> a fourth argument type.
 * @param <T5> a fifth argument type.
 * @param <T6> a sixth argument type.
 */
@FunctionalInterface
public interface Consumer6<T1, T2, T3, T4, T5, T6> {

  /**
   * Applies function.
   *
   * @param arg1 a first argument.
   * @param arg2 a second argument.
   * @param arg3 a third argument.
   * @param arg4 a fourth argument.
   * @param arg5 a fifth argument.
   * @param arg6 a sixth argument.
   */
  void accept(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6);
}
