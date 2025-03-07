package tech.intellispaces.commons.entity;

/**
 * Entity reference provider.
 */
public interface References {

  static <E> Reference<E> get(E entity) {
    return new DirectReferenceImpl<>(entity);
  }
}
