package tech.intellispaces.entity.type;

/**
 * Primitive related functions.
 */
public interface PrimitiveFunctions {

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
