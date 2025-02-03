package tech.intellispaces.commons.base.function.primitive;

/**
 * Function that accepts single object argument and single double primitive argument and returns double primitive.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectAndDoubleToDoubleFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  double apply(T arg1, double arg2);
}
