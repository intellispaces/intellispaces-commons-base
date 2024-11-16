package tech.intellispaces.entity.function.primitive;

import java.util.function.ToIntFunction;

/**
 * Function that accepts single object argument and returns integer primitive result.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectToIntFunction<T> extends ToIntFunction<T> {

  /**
   * Applies function.
   *
   * @param arg the argument.
   * @return the function result.
   */
  int applyAsInt(T arg);
}
