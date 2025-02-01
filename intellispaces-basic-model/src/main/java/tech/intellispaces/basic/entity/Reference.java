package tech.intellispaces.basic.entity;

/**
 * Entity reference.
 *
 * @param <E> the referenced entity type.
 */
public interface Reference<E> {

  /**
   * Reference target.
   */
  E get();
}
