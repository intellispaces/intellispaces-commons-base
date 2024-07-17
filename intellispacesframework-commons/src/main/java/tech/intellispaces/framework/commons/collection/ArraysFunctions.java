package tech.intellispaces.framework.commons.collection;

import java.lang.reflect.Array;
import java.util.function.Function;

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

  static <E, V> boolean contains(E[] array, Function<E, V> mapper, V value) {
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
        if (mapper.apply(e).equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  static <S, T> T[] map(S[] array, Function<S, T> mapper, Class<T> targetClass) {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return (T[]) Array.newInstance(targetClass, 0);
    }
    T[] result = (T[]) Array.newInstance(targetClass, array.length);
    for (int i = 0; i < array.length; i++) {
      result[i] = mapper.apply(array[i]);
    }
    return result;
  }
}
