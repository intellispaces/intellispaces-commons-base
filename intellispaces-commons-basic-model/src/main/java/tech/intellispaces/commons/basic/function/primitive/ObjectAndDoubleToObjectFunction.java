package tech.intellispaces.commons.basic.function.primitive;

/**
 * Function that accepts single object argument and single double primitive argument and returns object value.
 *
 * @param <T> the object argument type.
 * @param <R> the returned object type.
 */
@FunctionalInterface
public interface ObjectAndDoubleToObjectFunction<T, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  R apply(T arg1, double arg2);
}
