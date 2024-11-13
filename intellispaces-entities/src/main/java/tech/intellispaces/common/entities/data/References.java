package tech.intellispaces.common.entities.data;

/**
 * Reference provider.
 */
public interface References {

  static <T> Reference<T> get(T target) {
    return new DirectReferenceImpl<>(target);
  }
}
