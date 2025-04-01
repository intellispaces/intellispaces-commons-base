package tech.intellispaces.commons.function;

/**
 * Function that accepts three arguments
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 * @param <R> a function result type.
 */
@FunctionalInterface
public interface Function3<T1, T2, T3, R> {

  /**
   * Applies function.
   *
   * @param arg1 a first argument.
   * @param arg2 a second argument.
   * @param arg3 a third argument.
   * @return a function result.
   */
  R apply(T1 arg1, T2 arg2, T3 arg3);
}
