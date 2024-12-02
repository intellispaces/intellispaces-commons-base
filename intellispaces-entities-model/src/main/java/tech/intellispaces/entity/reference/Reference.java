package tech.intellispaces.entity.reference;

/**
 * Reference to something.
 *
 * @param <T> the reference target type.
 */
public interface Reference<T> {

  /**
   * Reference target.
   */
  T get();
}
