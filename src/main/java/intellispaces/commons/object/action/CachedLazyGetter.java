package intellispaces.commons.object.action;

import intellispaces.commons.model.action.Getter;

import java.util.function.Supplier;

public class CachedLazyGetter<T> implements Getter<T> {
  private final Supplier<T> supplier;
  private T value;
  private boolean performed;

  public CachedLazyGetter(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  @Override
  public T execute() {
    if (!performed) {
      value = supplier.get();
      performed = true;
    }
    return value;
  }
}
