package tech.intellispaces.general.function.primitive;

/**
 * Function that accepts two object arguments and returns integer primitive result.
 *
 * @param <T1> the first object argument type.
 * @param <T2> the second object argument type.
 */
@FunctionalInterface
public interface TwoObjectsToIntFunction<T1, T2> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  int apply(T1 arg1, T2 arg2);
}
