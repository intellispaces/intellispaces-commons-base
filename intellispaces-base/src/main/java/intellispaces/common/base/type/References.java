package intellispaces.common.base.type;

public interface References {

  static <T> Reference<T> get(T target) {
    return new DirectReference<>(target);
  }
}
