package intellispaces.commons.action;

import java.util.function.Supplier;

/**
 * Getter action.<p/>
 *
 * Getter is action to return a value.
 *
 * @param <T> getter result value type.
 */
public interface GetterAction<T> extends Action, Supplier<T> {

  T perform();

  @Override
  default T get() {
    return perform();
  }
}
