package tech.intellispaces.commons.basic.function.primitive;

/**
 * Function that accepts two object arguments and returns double primitive result.
 *
 * @param <T1> the first object argument type.
 * @param <T2> the second object argument type.
 */
@FunctionalInterface
public interface TwoObjectsToDoubleFunction<T1, T2> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  double apply(T1 arg1, T2 arg2);
}
