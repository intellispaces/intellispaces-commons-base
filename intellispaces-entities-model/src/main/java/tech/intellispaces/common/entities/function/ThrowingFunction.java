package tech.intellispaces.common.entities.function;

/**
 * Function that accepts single argument and can throw exception.
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
   * @throws E the exception type.
   */
  R applyThrows(T t) throws E;
}