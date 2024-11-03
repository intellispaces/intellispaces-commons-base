package intellispaces.common.base.type;

import java.util.List;

public interface Classes {

  @SuppressWarnings("unchecked")
  static <T> Class<T> of(Class<? super T> aClass) {
    return (Class<T>) aClass;
  }

  @SuppressWarnings("unchecked,rawtypes")
  static <E> Class<List<E>> ofList(Class<E> elementClass) {
    return (Class) List.class;
  }
}
