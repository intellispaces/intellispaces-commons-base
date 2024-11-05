package intellispaces.common.base.type;

/**
 * Data pair type.
 *
 * @param <T1> the first data type.
 * @param <T2> the second data type.
 */
public interface Pair<T1, T2> {

  /**
   * First data value.
   */
  T1 value1();

  /**
   * Second data value.
   */
  T2 value2();
}
