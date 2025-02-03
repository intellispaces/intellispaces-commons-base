package tech.intellispaces.commons.base.function.primitive;

/**
 * Function that accepts single object argument and two double primitive arguments and returns integer primitive result.
 *
 * @param <T> object argument type.
 */
@FunctionalInterface
public interface ObjectAndTwoDoublesToIntFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  int apply(T arg1, double arg2, double arg3);
}
