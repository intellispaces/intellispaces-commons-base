package intellispaces.common.base.function.primitive;

/**
 * Function that accepts single object argument and two double primitive arguments and returns object result.
 *
 * @param <T> the object argument type.
 * @param <R> the result type.
 */
@FunctionalInterface
public interface ObjectAndTwoDoublesToObjectFunction<T, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  R apply(T arg1, double arg2, double arg3);
}
