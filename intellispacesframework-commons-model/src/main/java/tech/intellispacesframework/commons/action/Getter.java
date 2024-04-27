package tech.intellispacesframework.commons.action;

import java.util.function.Supplier;

/**
 * Getter action.
 *
 * <p>Getter is action to return a value.
 *
 * @param <T> getter result value type.
 */
public interface Getter<T> extends Action, Supplier<T> {

  T execute();

  @Override
  default T get() {
    return execute();
  }
}
