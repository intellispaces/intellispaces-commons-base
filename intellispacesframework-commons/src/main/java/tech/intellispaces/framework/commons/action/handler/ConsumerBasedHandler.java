package tech.intellispaces.framework.commons.action.handler;

import java.util.function.Consumer;

public class ConsumerBasedHandler<D> extends AbstractHandler<D> {
  private final Consumer<D> consumer;

  public ConsumerBasedHandler(Consumer<D> consumer) {
    this.consumer = consumer;
  }

  @Override
  public void handle(D data) {
    consumer.accept(data);
  }
}
