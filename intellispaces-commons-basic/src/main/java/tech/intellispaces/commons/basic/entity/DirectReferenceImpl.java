package tech.intellispaces.commons.basic.entity;

class DirectReferenceImpl<E> implements Reference<E> {
  private final E entity;

  DirectReferenceImpl(E entity) {
    this.entity = entity;
  }

  @Override
  public E get() {
    return entity;
  }
}
