package tech.intellispaces.commons.basic.function;

/**
 * Consumer that accept two arguments and can throw exception.
 *
 * @param <T1> the first input type.
 * @param <T2> the second input type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowingBiConsumer<T1, T2, E extends Exception> {

  /**
   * Applies consumer.
   *
   * @param t1 the first argument.
   * @param t2 the second argument.
   * @throws E the exception type.
   */
  void acceptThrows(T1 t1, T2 t2) throws E;
}
