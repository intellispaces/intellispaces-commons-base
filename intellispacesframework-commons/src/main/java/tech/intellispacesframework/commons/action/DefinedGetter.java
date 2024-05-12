package tech.intellispacesframework.commons.action;

public class DefinedGetter<T> implements Getter<T> {
  private T value;

  public DefinedGetter() {
  }

  public DefinedGetter(T value) {
    this.value = value;
  }

  public void set(T value) {
    this.value = value;
  }

  @Override
  public T execute() {
    return value;
  }
}
