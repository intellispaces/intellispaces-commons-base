package intellispaces.common.base.function;

/**
 * Function that accepts single argument and can throw exception.
 *
 * @param <T> the function argument type.
 * @param <R> the function result type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, E extends Throwable> {

  /**
   * Applies function.
   *
   * @param t the function argument.
   * @return the function result.
   * @throws E the exception type.
   */
  R apply(T t) throws E;
}