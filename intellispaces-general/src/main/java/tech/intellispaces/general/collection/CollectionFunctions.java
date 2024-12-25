package tech.intellispaces.general.collection;

import tech.intellispaces.general.function.ThrowingBiFunction;
import tech.intellispaces.general.function.ThrowingFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * Collection processing functions.
 */
public interface CollectionFunctions {

  static <T> void addIfNotNull(List<T> list, T value) {
    if (value != null) {
      list.add(value);
    }
  }

  /**
   * Creates collection that contain given collection and additional values.
   *
   * @param collection the source collection.
   * @param other additional value.
   * @return result collection.
   * @param <T> collection element type.
   */
  static <T> Collection<T> join(Collection<T> collection, T... other) {
    if (collection == null) {
      collection = List.of();
    }

    List<T> result = new ArrayList<>(collection);
    if (other != null && other.length > 0) {
      result.addAll(Arrays.stream(other).toList());
    }
    return Collections.unmodifiableCollection(result);
  }

  static <E> boolean containsAnyOf(Collection<E> collection, E value1, E value2) {
    Objects.requireNonNull(collection);
    for (E e : collection) {
      if (Objects.equals(e, value1) || Objects.equals(e, value2)) {
        return true;
      }
    }
    return false;
  }

  static <E> boolean containsAnyOf(Collection<E> collection, E value1, E value2, E value3) {
    Objects.requireNonNull(collection);
    for (E e : collection) {
      if (Objects.equals(e, value1)
          || Objects.equals(e, value2)
          || Objects.equals(e, value3)) {
        return true;
      }
    }
    return false;
  }

  static <E> boolean containsAnyOf(Collection<E> collection, E value1, E value2, E value3, E value4) {
    Objects.requireNonNull(collection);
    for (E e : collection) {
      if (Objects.equals(e, value1)
          || Objects.equals(e, value2)
          || Objects.equals(e, value3)
          || Objects.equals(e, value4)
      ) {
        return true;
      }
    }
    return false;
  }

  static <E> boolean containsAnyOf(Collection<E> collection, E value1, E value2, E value3, E value4, E value5) {
    Objects.requireNonNull(collection);
    for (E e : collection) {
      if (Objects.equals(e, value1)
          || Objects.equals(e, value2)
          || Objects.equals(e, value3)
          || Objects.equals(e, value4)
          || Objects.equals(e, value5)
      ) {
        return true;
      }
    }
    return false;
  }

  @SafeVarargs
  static <E> boolean containsAnyOf(Collection<E> collection, E... values) {
    Objects.requireNonNull(collection);
    for (E e : collection) {
      for (E v : values) {
        if (Objects.equals(e, v)) {
          return true;
        }
      }
    }
    return false;
  }

  static <E1, E2, E extends Exception> List<E2> mapEach(
      Collection<E1> source, ThrowingFunction<E1, E2, E> mapper
  ) throws E {
    if (source == null) {
      return null;
    }
    var result = new ArrayList<E2>(source.size());
    for (E1 e1 : source) {
      result.add(mapper.applyThrows(e1));
    }
    return result;
  }

  static <E1, E2, E extends Exception> List<E2> mapEach(
      Collection<E1> source, ThrowingBiFunction<E1, Integer, E2, E> mapper
  ) throws E {
    if (source == null) {
      return null;
    }
    var result = new ArrayList<E2>(source.size());
    int index = 0;
    for (E1 e1 : source) {
      result.add(mapper.applyThrows(e1, index++));
    }
    return result;
  }

  static <E> List<E> toList(Enumeration<E> enumeration) {
    if (enumeration == null) {
      return null;
    }
    List<E> list = new ArrayList<>();
    while (enumeration.hasMoreElements()) {
      list.add(enumeration.nextElement());
    }
    return list;
  }
}
