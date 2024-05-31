package tech.intellispaces.framework.commons.action;

import java.util.function.Supplier;

/**
 * Getter action.
 *
 * <p>Getter is action that returns a data.
 *
 * @param <T> result data type.
 */
@FunctionalInterface
public interface Getter<T> extends Action, Supplier<T> {

  @Override
  default void execute() {
    get();
  }
}
