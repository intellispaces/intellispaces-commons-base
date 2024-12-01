package tech.intellispaces.entity.reference;

class DirectReferenceImpl<T> implements Reference<T> {
  private final T target;

  DirectReferenceImpl(T target) {
    this.target = target;
  }

  @Override
  public T get() {
    return target;
  }
}
