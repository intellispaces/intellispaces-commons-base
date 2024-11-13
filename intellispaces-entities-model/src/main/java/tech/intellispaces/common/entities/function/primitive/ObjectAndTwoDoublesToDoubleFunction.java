package tech.intellispaces.common.entities.function.primitive;

/**
 * Function that accepts single object argument and two double primitive arguments and returns double primitive result.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectAndTwoDoublesToDoubleFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  double apply(T arg1, double arg2, double arg3);
}
