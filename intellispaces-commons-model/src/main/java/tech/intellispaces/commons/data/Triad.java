package tech.intellispaces.commons.data;

/**
 * Data triad type.
 *
 * @param <T1> the first data type.
 * @param <T2> the second data type.
 * @param <T3> the third data type.
 */
public interface Triad<T1, T2, T3> {

  /**
   * First data value.
   */
  T1 value1();

  /**
   * Second data value.
   */
  T2 value2();

  /**
   * Third data value.
   */
  T3 value3();
}
