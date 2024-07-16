package tech.intellispaces.framework.commons.type;

public final class Type<T> {
  private final Class<? super T> superClass;

  public Type(Class<? super T> superClass) {
    this.superClass = superClass;
  }

  public Class<? super T> superClass() {
    return superClass;
  }
}
