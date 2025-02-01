package tech.intellispaces.basic.function.primitive;

/**
 * Function that accepts two object arguments and single double primitive argument and returns integer primitive
 * result.
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 */
@FunctionalInterface
public interface TwoObjectsAndDoubleToIntFunction<T1, T2> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  int apply(T1 arg1, T2 arg2, double arg3);
}
