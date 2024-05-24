package tech.intellispacesframework.commons.action;

import java.util.function.Consumer;

/**
 * Handler action.
 *
 * <p>Handler is action that processes an input data.
 *
 * @param <T> input data type.
 */
@FunctionalInterface
public interface Handler<T> extends Action, Consumer<T> {

  /**
   * Executes action.
   *
   * @param value input data.
   */
  void handle(T value);

  @Override
  default void execute() {
    throw new UnsupportedOperationException("There is no input data");
  }

  @Override
  default void accept(T value) {
    handle(value);
  }
}
