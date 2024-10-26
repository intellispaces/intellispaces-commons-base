package intellispaces.common.base.type;

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
}
