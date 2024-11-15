package tech.intellispaces.entities.function.primitive;

/**
 * Function that accepts single object argument and returns integer primitive result.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectToIntFunction<T> {

  /**
   * Applies function.
   *
   * @param arg the argument.
   * @return the function result.
   */
  int apply(T arg);
}
