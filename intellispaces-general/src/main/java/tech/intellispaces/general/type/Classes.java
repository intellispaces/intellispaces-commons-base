package tech.intellispaces.general.type;

import java.util.List;
import java.util.Optional;

/**
 * Class provider.
 */
public interface Classes {

  static Optional<Class<?>> get(String className) {
    return ClassFunctions.getClass(className);
  }

  /**
   * Generic unchecked cast.
   *
   * @param aClass base class.
   * @return result class.
   * @param <T> target type.
   */
  @SuppressWarnings("unchecked")
  static <T> Class<T> genericCast(Class<? super T> aClass) {
    return (Class<T>) aClass;
  }

  /**
   * Returns casted list class.
   *
   * @param elementClass list element class.
   * @return casted list class.
   * @param <E> list element type.
   */
  @SuppressWarnings("unchecked,rawtypes")
  static <E> Class<List<E>> ofList(Class<E> elementClass) {
    return (Class) List.class;
  }
}
