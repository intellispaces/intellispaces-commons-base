package tech.intellispaces.common.entities.data;

/**
 * Reference to something.
 *
 * @param <T> reference target type.
 */
public interface Reference<T> {

  /**
   * Reference target.
   */
  T get();

  /**
   * Ordinal representation of the reference.
   */
  int asOrdinal();

  /**
   * String key representation of the reference.
   */
  String asKey();
}
