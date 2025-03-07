package tech.intellispaces.commons.collection;

import tech.intellispaces.commons.function.ThrowingBiFunction;
import tech.intellispaces.commons.function.ThrowingConsumer;
import tech.intellispaces.commons.function.ThrowingFunction;

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

  static <E> boolean isNullOrEmpty(Collection<E> collection) {
    return collection == null || collection.isEmpty();
  }

  static <T> void addIfNotNull(List<T> list, T value) {
    if (value != null) {
      list.add(value);
    }
  }

  /**
   * Creates collection that contain given collection and additional elements.
   *
   * @param collection the source collection.
   * @param other additional element(s).
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

  /**
   * Creates list that contain given list and additional elements.
   *
   * @param list the source list.
   * @param other additional element(s).
   * @return result list.
   * @param <T> list element type.
   */
  static <T> List<T> join(List<T> list, T... other) {
    if (list == null) {
      list = List.of();
    }

    List<T> result = new ArrayList<>(list);
    if (other != null && other.length > 0) {
      result.addAll(Arrays.stream(other).toList());
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * Creates list that contain given list and additional elements.
   *
   * @param list the source list.
   * @param other additional elements.
   * @return result list.
   * @param <T> list element type.
   */
  static <T> List<T> join(List<T> list, List<T> other) {
    if (list == null && other == null) {
      list = List.of();
    }
    if (list == null) {
      return other;
    }
    if (other == null) {
      return list;
    }

    List<T> result = new ArrayList<>(list);
    if (other != null) {
      result.addAll(other);
    }
    return Collections.unmodifiableList(result);
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

  static <E1, E2, Ex extends Exception> List<E2> mapEach(
      Collection<E1> source, ThrowingFunction<E1, E2, Ex> mapper
  ) throws Ex {
    if (source == null) {
      return null;
    }
    var result = new ArrayList<E2>(source.size());
    for (E1 e1 : source) {
      result.add(mapper.applyThrows(e1));
    }
    return result;
  }

  static <E1, E2, Ex extends Exception> List<E2> mapEach(
      Collection<E1> source, ThrowingBiFunction<E1, Integer, E2, Ex> mapper
  ) throws Ex {
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

  static <E, Ex extends Exception> void forEach(
      Collection<E> collection, ThrowingConsumer<E, Ex> consumer
  ) throws Ex {
    if (collection == null) {
      return;
    }
    for (E e : collection) {
      consumer.acceptThrows(e);
    }
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
