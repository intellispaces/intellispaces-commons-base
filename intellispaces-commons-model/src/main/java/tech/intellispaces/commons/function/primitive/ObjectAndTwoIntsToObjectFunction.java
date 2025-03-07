package tech.intellispaces.commons.function.primitive;

/**
 * Function that accepts single object argument and two integer primitive arguments and return object result.
 *
 * @param <T> the object argument type.
 * @param <R> the result type.
 */
@FunctionalInterface
public interface ObjectAndTwoIntsToObjectFunction<T, R> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @return the function result.
   */
  R apply(T arg1, int arg2, int arg3);
}
