package intellispaces.common.base.function;

/**
 * Function that accepts three arguments
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 * @param <T3> the third argument type.
 * @param <R> the function result type.
 */
@FunctionalInterface
public interface TriFunction<T1, T2, T3, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  R apply(T1 arg1, T2 arg2, T3 arg3);
}
