package intellispaces.common.base.function;

import intellispaces.common.base.exception.CoveredCheckedException;

import java.util.function.Consumer;
import java.util.function.Function;

public final class Consumers {

  private Consumers() {}

  public static <T, E extends Throwable> Consumer<T> coveredThrowableConsumer(
      ThrowableConsumer<T, E> consumer
  ) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw new CoveredCheckedException(e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  public static <T, E extends Throwable, E2 extends RuntimeException> Consumer<T> coveredThrowableConsumer(
      ThrowableConsumer<T, E> consumer, Function<E, E2> exceptionFactory
  ) {
    return t -> {
      try {
        consumer.accept(t);
      } catch (RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw exceptionFactory.apply((E) e);
      }
    };
  }

  @SuppressWarnings("unchecked")
  public static <T> Consumer<T> idle() {
    return (Consumer<T>) IDLE_CONSUMER;
  }

  private static final Consumer<?> IDLE_CONSUMER = v -> {};
}
