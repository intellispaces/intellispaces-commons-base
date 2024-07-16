package tech.intellispaces.framework.commons.action.onetime;

import tech.intellispaces.framework.commons.action.Action;

import java.util.function.Function;

public class CachedFirstTimeOnlyAction<V, D1, D2, D3, D4, D5> implements Action<V, D1, D2, D3, D4, D5> {
  private final Action<V, D1, D2, D3, D4, D5> action;
  private boolean first = true;
  private V cachedValue = null;

  public CachedFirstTimeOnlyAction(Action<V, D1, D2, D3, D4, D5> action) {
    this.action = action;
  }

  @Override
  public V execute(D1 data1, D2 data2, D3 data3, D4 data4, D5 data5) {
    if (first) {
      cachedValue = action.execute(data1, data2, data3, data4, data5);
      first = false;
    }
    return cachedValue;
  }

  @Override
  public <A extends Action<_V, _D1, _D2, _D3, _D4, _D5>, _V, _D1, _D2, _D3, _D4, _D5> A wrapAction(
      Function<Action<V, D1, D2, D3, D4, D5>, A> wrapperFactory
  ) {
    return wrapperFactory.apply(this);
  }
}
