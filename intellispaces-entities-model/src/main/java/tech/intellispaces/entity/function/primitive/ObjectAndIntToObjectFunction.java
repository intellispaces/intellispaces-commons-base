package tech.intellispaces.entity.function.primitive;

/**
 * Function that accepts single object argument and single integer primitive argument and returns object result.
 *
 * @param <T> the object argument type.
 * @param <R> the result type.
 */
@FunctionalInterface
public interface ObjectAndIntToObjectFunction<T, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  R apply(T arg1, int arg2);
}
