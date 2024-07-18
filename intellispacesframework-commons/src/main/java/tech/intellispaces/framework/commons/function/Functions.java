package tech.intellispaces.framework.commons.function;

import java.util.function.Consumer;

public final class Functions {

  @SuppressWarnings("unchecked")
  public static <T> Consumer<T> idleConsumer() {
    return (Consumer<T>) IDLE_CONSUMER;
  }

  private static final Consumer<?> IDLE_CONSUMER = v -> {};
}
