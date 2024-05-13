package tech.intellispacesframework.commons.action;

public class ResettableGetter<T> implements SettableGetter<T> {
  private T value;

  public ResettableGetter() {
  }

  public ResettableGetter(T value) {
    this.value = value;
  }

  @Override
  public void set(T value) {
    this.value = value;
  }

  @Override
  public T execute() {
    return value;
  }
}
