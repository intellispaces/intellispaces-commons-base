package tech.intellispaces.general.data;

/**
 * Data triad provider.
 */
public interface Triads {

  static <T1, T2, T3> Triad<T1, T2, T3> get(T1 value1, T2 value2, T3 value3) {
    return new TriadImpl<>(value1, value2, value3);
  }
}
