package tech.intellispaces.commons.data;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UuidFunctions} class.
 */
public class UuidFunctionsTest {

  @Test
  public void testUuidToBytes() {
    // Given
    var uuid = UUID.fromString("acbe13a4-8520-4467-a856-2ea1890c98b5");

    // When
    byte[] bytes = UuidFunctions.uuidToBytes(uuid);

    // Then
    assertThat(bytes).isEqualTo(new byte[] {
        -84, -66, 19, -92, -123, 32, 68, 103, -88, 86, 46, -95, -119, 12, -104, -75
    });
  }
}
