package intellispaces.common.base.function.primitive;

/**
 * Function that accepts single object argument and returns double primitive result.
 *
 * @param <T> the object argument type.
 */
@FunctionalInterface
public interface ObjectToDoubleFunction<T> {

  /**
   * Applies function.
   *
   * @param arg the argument.
   * @return the function result.
   */
  double apply(T arg);
}
