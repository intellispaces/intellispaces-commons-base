package tech.intellispaces.general.collection;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Arrays processing functions.
 */
public interface ArraysFunctions {

  static byte[] toByteArray(List<Byte> bytes) {
    if (bytes == null) {
      return null;
    }

    byte[] arr = new byte[bytes.size()];
    int ind = 0;
    for (byte b : bytes) {
      arr[ind++] = b;
    }
    return arr;
  }

  static List<Byte> toByteList(byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    return IntStream.range(0, bytes.length).mapToObj(i -> bytes[i]).toList();
  }

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

  static <E> boolean containsAny(E[] array, E value1, E value2) {
    if (array == null) {
      return false;
    }
    if (value1 == null || value2 == null) {
      for (E e : array) {
        if (e == null) {
          return true;
        }
      }
    } else {
      for (E e : array) {
        if (e.equals(value1) || e.equals(value2)) {
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

  static InputStream arrayToInputStream(byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    if (bytes.length == 0) {
      return InputStream.nullInputStream();
    }
    return new ByteArrayInputStream(bytes);
  }
}
