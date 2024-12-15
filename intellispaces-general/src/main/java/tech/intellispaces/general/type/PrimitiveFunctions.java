package tech.intellispaces.general.type;

/**
 * Primitive related functions.
 */
public interface PrimitiveFunctions {

  static boolean isPrimitiveTypename(String typename) {
    for (var type : PrimitiveTypes.values()) {
      if (type.typename().equals(typename)) {
        return true;
      }
    }
    return false;
  }

  static int booleanToInt(boolean value) {
    return value ? 1 : 0;
  }

  static long booleanToLong(boolean value) {
    return value ? 1L : 0L;
  }

  static double booleanToDouble(boolean value) {
    return value ? 1.0 : 0.0;
  }

  static String booleanToString(boolean value) {
    return value ? "true" : "false";
  }

  static boolean longToBoolean(long value) {
    return value != 0L;
  }
}
