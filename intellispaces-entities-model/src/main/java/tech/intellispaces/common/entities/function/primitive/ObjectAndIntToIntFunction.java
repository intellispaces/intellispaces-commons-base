package tech.intellispaces.common.entities.function.primitive;

/**
 * Function that accepts single object argument and single integer primitive argument and returns integer primitive
 * result.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectAndIntToIntFunction<T> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @return the function result.
   */
  int apply(T arg1, int arg2);
}
