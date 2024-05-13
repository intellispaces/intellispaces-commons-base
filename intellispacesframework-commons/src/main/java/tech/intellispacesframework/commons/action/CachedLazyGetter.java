package tech.intellispacesframework.commons.action;

import java.util.function.Supplier;

public class CachedLazyGetter<T> implements Getter<T> {
  private final Supplier<T> supplier;
  private T cache;
  private boolean isCached;

  public CachedLazyGetter(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  @Override
  public T execute() {
    if (!isCached) {
      cache = supplier.get();
      isCached = true;
    }
    return cache;
  }
}
