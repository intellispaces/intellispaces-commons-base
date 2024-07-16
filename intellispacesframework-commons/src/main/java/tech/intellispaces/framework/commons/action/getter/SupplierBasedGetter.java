package tech.intellispaces.framework.commons.action.getter;

import tech.intellispaces.framework.commons.action.Getter;

import java.util.function.Supplier;

public class SupplierBasedGetter<V> extends AbstractGetter<V> implements Getter<V> {
  private final Supplier<V> supplier;

  public SupplierBasedGetter(Supplier<V> supplier) {
    this.supplier = supplier;
  }

  @Override
  public V get() {
    return supplier.get();
  }
}
