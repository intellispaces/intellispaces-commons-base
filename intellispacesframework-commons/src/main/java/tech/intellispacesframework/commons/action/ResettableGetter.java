package tech.intellispacesframework.commons.action;

public class ResettableGetter<T> extends AbstractAction implements SettableGetter<T> {
  private T value;

  public ResettableGetter() {
  }

  public ResettableGetter(T value) {
    this.value = value;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public void set(T value) {
    this.value = value;
  }
}
