package tech.intellispaces.entities.function;

import tech.intellispaces.entities.exception.WrappedException;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Consumer provider.
 */
public final class Consumers {

  public static <T, E extends Exception> Consumer<T> wrappedConsumer(
      ThrowingConsumer<T, E> consumer
  ) {
    return t -> {
      try {
        consumer.acceptThrows(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw new WrappedException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  public static <T, E extends Exception, E2 extends RuntimeException> Consumer<T> wrappedConsumer(
      ThrowingConsumer<T, E> consumer, Function<E, E2> exceptionFactory
  ) {
    return t -> {
      try {
        consumer.acceptThrows(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Exception e) {
        throw exceptionFactory.apply((E) e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  public static <T> Consumer<T> idle() {
    return (Consumer<T>) IDLE_CONSUMER;
  }

  public static <T> Consumer<T> idle(Class<T> valueClass) {
    return idle();
  }

  private Consumers() {}

  private static final Consumer<?> IDLE_CONSUMER = v -> {};
}
