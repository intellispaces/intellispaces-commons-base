package intellispaces.commons.object.action;

import intellispaces.commons.model.action.GetterAction;

import java.util.function.Supplier;

public class CachedLazyGetterAction<T> implements GetterAction<T> {
  private final Supplier<T> supplier;
  private T value;
  private boolean performed;

  public CachedLazyGetterAction(Supplier<T> supplier) {
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
