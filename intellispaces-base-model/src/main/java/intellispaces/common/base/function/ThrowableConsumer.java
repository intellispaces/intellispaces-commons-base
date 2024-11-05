package intellispaces.common.base.function;

/**
 * Consumer that accept single argument and can throw exception.
 *
 * @param <T> the consumer input type.
 * @param <E> the exception type.
 */
@FunctionalInterface
public interface ThrowableConsumer<T, E extends Throwable> {

  /**
   * Applies consumer.
   *
   * @param t the argument.
   * @throws E the exception type.
   */
  void accept(T t) throws E;
}
