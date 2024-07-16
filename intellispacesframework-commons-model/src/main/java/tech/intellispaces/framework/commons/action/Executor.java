package tech.intellispaces.framework.commons.action;

import java.util.function.Function;

public interface Executor extends Action0<Void> {

  Executor then(Action0<Void> otherAction);

  Executor wrap(
      Function<Action<Void, Void, Void, Void, Void, Void>, Action<Void, Void, Void, Void, Void, Void>> wrapperFactory
  );
}
