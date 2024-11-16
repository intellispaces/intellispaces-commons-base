package tech.intellispaces.entity.data;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * UUID related functions.
 */
public interface UuidFunctions {

  static byte[] uuidToBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }
}
