package intellispaces.common.base.function;

/**
 * Function that accepts five arguments.
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 * @param <T3> the third argument type.
 * @param <T4> the fourth argument type.
 * @param <T5> the fifth argument type.
 * @param <R> the function result type.
 */
@FunctionalInterface
public interface QuinFunction<T1, T2, T3, T4, T5, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @param arg4 the fourth argument.
   * @param arg5 the fifth argument.
   * @return the function result.
   */
  R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
}
