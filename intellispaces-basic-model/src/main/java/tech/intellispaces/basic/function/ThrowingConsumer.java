package tech.intellispaces.basic.function;

/**
 * Consumer that accept single argument and can throw exception.
 *
 * @param <T> the consumer input type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {

  /**
   * Applies consumer.
   *
   * @param t the argument.
   * @throws E the exception type.
   */
  void acceptThrows(T t) throws E;
}
