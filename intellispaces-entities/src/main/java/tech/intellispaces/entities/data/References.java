package tech.intellispaces.entities.data;

/**
 * Reference provider.
 */
public interface References {

  static <T> Reference<T> get(T target) {
    return new DirectReferenceImpl<>(target);
  }
}
