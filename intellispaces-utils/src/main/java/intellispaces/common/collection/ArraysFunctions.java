package intellispaces.common.collection;

import java.lang.reflect.Array;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Arrays processing functions.
 */
public interface ArraysFunctions {

  static <T> void foreach(T[] array, Consumer<T> consumer) {
    if (array != null) {
      for (T t : array) {
        consumer.accept(t);
      }
    }
  }

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

  static Boolean[] wrap(boolean[] elements) {
    if (elements == null) {
      return null;
    }
    return IntStream.range(0, elements.length).mapToObj(ind -> elements[ind]).toArray(Boolean[]::new);
  }

  static Integer[] wrap(int[] elements) {
    if (elements == null) {
      return null;
    }
    return IntStream.of(elements).boxed().toArray(Integer[]::new);
  }

  static Double[] wrap(double[] elements) {
    if (elements == null) {
      return null;
    }
    return DoubleStream.of(elements).boxed().toArray(Double[]::new);
  }
}
