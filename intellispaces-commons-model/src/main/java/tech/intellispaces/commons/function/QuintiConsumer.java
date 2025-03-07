package tech.intellispaces.commons.function;

/**
 * Consumer that accepts five arguments.
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 * @param <T3> the third argument type.
 * @param <T4> the fourth argument type.
 * @param <T5> the fifth argument type.
 */
@FunctionalInterface
public interface QuintiConsumer<T1, T2, T3, T4, T5> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @param arg4 the fourth argument.
   * @param arg5 the fifth argument.
   */
  void accept(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
}
