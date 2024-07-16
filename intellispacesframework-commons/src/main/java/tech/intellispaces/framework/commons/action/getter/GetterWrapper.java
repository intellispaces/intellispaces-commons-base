package tech.intellispaces.framework.commons.action.getter;

import tech.intellispaces.framework.commons.action.Action;

public class GetterWrapper<V> extends AbstractGetter<V> {
  private final Action<V, Void, Void, Void, Void, Void> action;

  public GetterWrapper(Action<V, Void, Void, Void, Void, Void> action) {
    this.action = action;
  }

  @Override
  public V get() {
    return action.execute(null, null, null, null, null);
  }
}
