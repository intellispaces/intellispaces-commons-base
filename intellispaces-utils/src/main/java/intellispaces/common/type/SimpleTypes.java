package intellispaces.common.type;

public interface SimpleTypes {

  static <T> Type<T> of (Class<T> aClass) {
    return new SimpleType<>(aClass);
  }
}
