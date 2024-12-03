package tech.intellispaces.general.data;

import java.util.Objects;

class TriadImpl<T1, T2, T3> implements Triad<T1, T2, T3> {
  private final T1 value1;
  private final T2 value2;
  private final T3 value3;

  TriadImpl(T1 value1, T2 value2, T3 value3) {
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
  }

  @Override
  public T1 value1() {
    return value1;
  }

  @Override
  public T2 value2() {
    return value2;
  }

  @Override
  public T3 value3() {
    return value3;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Triad<?, ?, ?> triad)) {
      return false;
    }
    return Objects.equals(value1, triad.value1())
        && Objects.equals(value2, triad.value2())
        && Objects.equals(value3, triad.value3());
  }

  @Override
  public int hashCode() {
    return Objects.hash(value1, value2, value3);
  }
}
