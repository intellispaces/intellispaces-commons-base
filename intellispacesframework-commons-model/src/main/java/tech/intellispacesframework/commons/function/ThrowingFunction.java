package tech.intellispacesframework.commons.function;

/**
 * Function with one argument throws exception.
 *
 * @param <T> the function argument type.
 * @param <R> the function result type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {

  /**
   * Applies function.
   *
   * @param t the function argument.
   * @return the function result.
   * @throws E the exception.
   */
  R apply(T t) throws E;
}