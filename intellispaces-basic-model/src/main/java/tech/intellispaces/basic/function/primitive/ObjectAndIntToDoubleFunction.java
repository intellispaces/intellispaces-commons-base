package tech.intellispaces.basic.function.primitive;

/**
 * Function that accepts single object argument and single integer primitive argument and returns double primitive result.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectAndIntToDoubleFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  double apply(T arg1, int arg2);
}
