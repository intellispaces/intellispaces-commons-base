package intellispaces.common.base.type;

class DirectReference<T> implements Reference<T> {
  private final T target;

  DirectReference(T target) {
    this.target = target;
  }

  @Override
  public T get() {
    return target;
  }
}
