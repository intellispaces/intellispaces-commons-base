package intellispaces.commons.math;

import java.nio.ByteBuffer;
import java.util.UUID;

public interface MathFunctions {

  static byte[] uuidToBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }
}
