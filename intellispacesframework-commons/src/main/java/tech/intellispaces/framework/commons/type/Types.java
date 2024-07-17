package tech.intellispaces.framework.commons.type;

public interface Types {

  static <T extends C, C> Type<T> of(Class<C> aClass) {
    return new Type<>(aClass);
  }
}
