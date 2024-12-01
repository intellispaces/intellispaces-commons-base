package tech.intellispaces.entity.reference;

/**
 * Reference provider.
 */
public interface References {

  static <T> Reference<T> get(T target) {
    return new DirectReferenceImpl<>(target);
  }
}
