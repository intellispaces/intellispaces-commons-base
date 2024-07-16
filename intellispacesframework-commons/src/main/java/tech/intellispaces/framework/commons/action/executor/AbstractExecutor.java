package tech.intellispaces.framework.commons.action.executor;

import tech.intellispaces.framework.commons.action.Action;
import tech.intellispaces.framework.commons.action.Action0;
import tech.intellispaces.framework.commons.action.Executor;
import tech.intellispaces.framework.commons.action.multi.MultiAction;

import java.util.function.Function;

abstract class AbstractExecutor implements Executor {

  @Override
  public Executor then(Action0<Void> otherAction) {
    return new ExecutorWrapper(new MultiAction<>(this, otherAction));
  }

  @Override
  public <A extends Action<_V, _D1, _D2, _D3, _D4, _D5>, _V, _D1, _D2, _D3, _D4, _D5> A wrapAction(
      Function<Action<Void, Void, Void, Void, Void, Void>, A> wrapperFactory
  ) {
    return wrapperFactory.apply(this);
  }

  @Override
  public Executor wrap(
      Function<Action<Void, Void, Void, Void, Void, Void>, Action<Void, Void, Void, Void, Void, Void>> wrapperFactory
  ) {
    return wrapperFactory.apply(this).wrapAction(ExecutorWrapper::new);
  }
}
