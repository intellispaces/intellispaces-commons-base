package tech.intellispaces.framework.commons.action;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Getter action.
 *
 * <p>Getter is action that returns a data.
 *
 * @param <V> result data type.
 */
public interface Getter<V> extends Action0<V>, Supplier<V> {

  @Override
  default V execute() {
    return get();
  }

  Getter<V> wrap(
      Function<Action<V, Void, Void, Void, Void, Void>, Action<V, Void, Void, Void, Void, Void>> wrapperFactory
  );
}
