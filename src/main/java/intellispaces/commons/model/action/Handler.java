package intellispaces.commons.model.action;

import java.util.function.Consumer;

/**
 * Handler action.
 *
 * <p>Handler is action that to process a value.
 */
public interface Handler<T> extends Action, Consumer<T> {

  void execute(T value);

  @Override
  default void accept(T value) {
    execute(value);
  }
}
