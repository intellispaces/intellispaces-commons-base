package tech.intellispaces.framework.commons.collection;

/**
 * Arrays processing functions.
 */
public interface ArraysFunctions {

  static <E> boolean contains(E[] array, E value) {
    if (array == null) {
      return false;
    }
    if (value == null) {
      for (E e : array) {
        if (e == null) {
          return true;
        }
      }
    } else {
      for (E e : array) {
        if (e.equals(value)) {
          return true;
        }
      }
    }
    return false;
  }
}
