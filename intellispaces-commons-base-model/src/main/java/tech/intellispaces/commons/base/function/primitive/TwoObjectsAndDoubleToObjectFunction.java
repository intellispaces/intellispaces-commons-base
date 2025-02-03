package tech.intellispaces.commons.base.function.primitive;

/**
 * Function that accepts two object arguments and single double primitive argument and returns object result.
 *
 * @param <T1> the first object argument type.
 * @param <T2> the second object argument type.
 * @param <R> the result type.
 */
@FunctionalInterface
public interface TwoObjectsAndDoubleToObjectFunction<T1, T2, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  R apply(T1 arg1, T2 arg2, double arg3);
}
