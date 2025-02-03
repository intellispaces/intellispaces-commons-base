package tech.intellispaces.commons.base.function.primitive;

/**
 * Function that accepts single object argument and single double argument and returns integer primitive.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectAndDoubleToIntFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  int apply(T arg1, double arg2);
}
