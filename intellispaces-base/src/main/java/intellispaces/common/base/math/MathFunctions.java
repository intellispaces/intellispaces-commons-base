package intellispaces.common.base.math;

import java.nio.ByteBuffer;
import java.util.UUID;

public interface MathFunctions {

  static byte[] uuidToBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
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

  static boolean longToBoolean(long value) {
    return value != 0L;
  }
}
