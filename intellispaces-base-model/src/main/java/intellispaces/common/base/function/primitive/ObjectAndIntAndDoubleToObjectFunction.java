package intellispaces.common.base.function.primitive;

/**
 * Function that accepts single object argument, single integer primitive argument and single double primitive argument
 * and returns object result.
 *
 * @param <T> the object argument type.
 * @param <R> the returned type.
 */
@FunctionalInterface
public interface ObjectAndIntAndDoubleToObjectFunction<T, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  R apply(T arg1, int arg2, double arg3);
}
