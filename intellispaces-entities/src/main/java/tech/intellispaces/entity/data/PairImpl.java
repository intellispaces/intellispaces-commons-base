package tech.intellispaces.entity.data;

import java.util.Objects;

class PairImpl<T1, T2> implements Pair<T1, T2> {
  private final T1 value1;
  private final T2 value2;

  PairImpl(T1 value1, T2 value2) {
    this.value1 = value1;
    this.value2 = value2;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !Pair.class.isAssignableFrom(o.getClass())) {
      return false;
    }
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(value1, pair.value1())
        && Objects.equals(value2, pair.value2());
  }

  @Override
  public int hashCode() {
    return Objects.hash(value1, value2);
  }
}
