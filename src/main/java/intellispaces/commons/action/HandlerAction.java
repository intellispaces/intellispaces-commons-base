package intellispaces.commons.action;

import java.util.function.Consumer;

/**
 * Handler action.<p/>
 *
 * Handler is action that to process a value.
 */
public interface HandlerAction<T> extends Action, Consumer<T> {

  void perform(T value);

  @Override
  default void accept(T value) {
    perform(value);
  }
}
