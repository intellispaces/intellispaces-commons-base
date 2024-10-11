package intellispaces.common.base.object;

import java.util.Objects;

public interface ObjectInstanceFunctions {

  static <E> boolean equalsAnyOf(E value, E value1, E value2) {
    if (Objects.equals(value, value1) || Objects.equals(value, value2)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E value1, E value2, E value3) {
    if (Objects.equals(value, value1) || Objects.equals(value, value2) || Objects.equals(value, value3)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E value1, E value2, E value3, E value4) {
    if (Objects.equals(value, value1) || Objects.equals(value, value2) || Objects.equals(value, value3) || Objects.equals(value, value4)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E value1, E value2, E value3, E value4, E value5) {
    if (Objects.equals(value, value1) || Objects.equals(value, value2) || Objects.equals(value, value3) || Objects.equals(value, value4) || Objects.equals(value, value5)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E... values) {
    for (E e : values) {
      if (Objects.equals(value, e)) {
        return true;
      }
    }
    return false;
  }
}
