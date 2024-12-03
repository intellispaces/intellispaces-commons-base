package tech.intellispaces.general.function.primitive;

/**
 * Function that accepts single
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectAndIntAndDoubleToIntFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  int apply(T arg1, int arg2, double arg3);
}
