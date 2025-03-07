package tech.intellispaces.commons.data;

/**
 * Data pair provider.
 */
public interface Pairs {

  static <T1, T2> Pair<T1, T2> get(T1 value1, T2 value2) {
    return new PairImpl<>(value1, value2);
  }
}
