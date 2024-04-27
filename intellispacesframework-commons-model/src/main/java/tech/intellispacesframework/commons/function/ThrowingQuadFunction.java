package tech.intellispacesframework.commons.function;

/**
 * The function with four arguments throws exception.
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 * @param <T3> the third argument type.
 * @param <T4> the fourth argument type.
 * @param <R> the function result type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowingQuadFunction<T1, T2, T3, T4, R, E extends Exception> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   * @param arg4 the fourth argument.
   * @return the function result.
   * @throws E the exception.
   */
  R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4) throws E;
}
