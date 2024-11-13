package tech.intellispaces.common.entities.data;

import tech.intellispaces.common.entities.exception.UnexpectedExceptions;

class DirectReferenceImpl<T> implements Reference<T> {
  private final T target;

  DirectReferenceImpl(T target) {
    this.target = target;
  }

  @Override
  public T get() {
    return target;
  }

  @Override
  public int asOrdinal() {
    throw UnexpectedExceptions.withMessage("This reference cannot be represented as an ordinal value");
  }

  @Override
  public String asKey() {
    throw UnexpectedExceptions.withMessage("This reference cannot be represented as an string key");
  }
}
