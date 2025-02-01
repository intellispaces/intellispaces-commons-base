package tech.intellispaces.commons.basic.entity;

/**
 * Entity reference provider.
 */
public interface References {

  static <E> Reference<E> get(E entity) {
    return new DirectReferenceImpl<>(entity);
  }
}
