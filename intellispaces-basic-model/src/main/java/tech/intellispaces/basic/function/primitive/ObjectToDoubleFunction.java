package tech.intellispaces.basic.function.primitive;

import java.util.function.ToDoubleFunction;

/**
 * Function that accepts single object argument and returns double primitive result.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectToDoubleFunction<T> extends ToDoubleFunction<T> {

  /**
   * Applies function.
   *
   * @param arg the argument.
   * @return the function result.
   */
  double applyAsDouble(T arg);
}
