package tech.intellispaces.framework.commons.action.getter;

import tech.intellispaces.framework.commons.action.SettableGetter;

public class ResettableGetter<V> extends AbstractGetter<V> implements SettableGetter<V> {
  private V value;

  public ResettableGetter() {
  }

  public ResettableGetter(V value) {
    this.value = value;
  }

  @Override
  public V get() {
    return value;
  }

  @Override
  public void set(V value) {
    this.value = value;
  }
}
