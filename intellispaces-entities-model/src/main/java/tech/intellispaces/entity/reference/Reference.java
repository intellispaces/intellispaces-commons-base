package tech.intellispaces.entity.reference;

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
}
