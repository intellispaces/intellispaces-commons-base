package tech.intellispaces.framework.commons.action.handler;

import tech.intellispaces.framework.commons.action.Action;
import tech.intellispaces.framework.commons.action.Action1;
import tech.intellispaces.framework.commons.action.Handler;
import tech.intellispaces.framework.commons.action.multi.MultiAction;

import java.util.function.Function;

abstract class AbstractHandler<D> implements Handler<D> {

  @Override
  public Handler<D> then(Action1<D, D> otherAction) {
    return new HandlerWrapper<>(new MultiAction<>(this, otherAction));
  }

  @Override
  public <A extends Action<_V, _D1, _D2, _D3, _D4, _D5>, _V, _D1, _D2, _D3, _D4, _D5> A wrapAction(
      Function<Action<D, D, Void, Void, Void, Void>, A> wrapperFactory
  ) {
    return wrapperFactory.apply(this);
  }

  @Override
  public Handler<D> wrap(
      Function<Action<D, D, Void, Void, Void, Void>, Action<D, D, Void, Void, Void, Void>> wrapperFactory
  ) {
    return this.wrapAction(wrapperFactory).wrapAction(HandlerWrapper::new);
  }
}
