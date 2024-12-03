package tech.intellispaces.general.function.primitive;

/**
 * Function that accepts single object argument, single integer primitive argument and single double primitive argument
 * and returns double primitive.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectAndIntAndDoubleToDoubleFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  double apply(T arg1, int arg2, double arg3);
}
