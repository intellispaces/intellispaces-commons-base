package tech.intellispaces.framework.commons.action.getter;

import tech.intellispaces.framework.commons.action.Action;
import tech.intellispaces.framework.commons.action.Getter;

import java.util.function.Function;

abstract class AbstractGetter<V> implements Getter<V> {

  @Override
  public <A extends Action<_V, _D1, _D2, _D3, _D4, _D5>, _V, _D1, _D2, _D3, _D4, _D5> A wrapAction(
      Function<Action<V, Void, Void, Void, Void, Void>, A> wrapperFactory
  ) {
    return wrapperFactory.apply(this);
  }

  @Override
  public Getter<V> wrap(
      Function<Action<V, Void, Void, Void, Void, Void>, Action<V, Void, Void, Void, Void, Void>> wrapperFactory
  ) {
    return wrapperFactory.apply(this).wrapAction(GetterWrapper::new);
  }
}
