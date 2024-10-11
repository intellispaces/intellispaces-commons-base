package intellispaces.common.base.object;

public interface ObjectInstanceFunctions {

  static <E> boolean equalsAnyOf(E value, E value1, E value2) {
    if (value.equals(value1) || value.equals(value2)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E value1, E value2, E value3) {
    if (value.equals(value1) || value.equals(value2) || value.equals(value3)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E value1, E value2, E value3, E value4) {
    if (value.equals(value1) || value.equals(value2) || value.equals(value3) || value.equals(value4)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E value1, E value2, E value3, E value4, E value5) {
    if (value.equals(value1) || value.equals(value2) || value.equals(value3) || value.equals(value4) || value.equals(value5)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E value, E... values) {
    for (E e : values) {
      if (value.equals(e)) {
        return true;
      }
    }
    return false;
  }
}
