package tech.intellispaces.commons.entity;

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
