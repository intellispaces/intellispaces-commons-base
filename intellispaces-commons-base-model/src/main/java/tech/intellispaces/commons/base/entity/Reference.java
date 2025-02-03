package tech.intellispaces.commons.base.entity;

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
