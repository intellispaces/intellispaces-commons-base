package intellispaces.common.base.math;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link MathFunctions}.
 */
public class MathFunctionsTest {

  @Test
  public void testUuidToBytes() {
    // Given
    var uuid = UUID.fromString("acbe13a4-8520-4467-a856-2ea1890c98b5");

    // When
    byte[] bytes = MathFunctions.uuidToBytes(uuid);

    // Then
    assertThat(bytes).isEqualTo(new byte[] {
        -84, -66, 19, -92, -123, 32, 68, 103, -88, 86, 46, -95, -119, 12, -104, -75
    });
  }

  @Test
  public void testBooleanToInt() {
    assertThat(MathFunctions.booleanToInt(true)).isEqualTo(1);
    assertThat(MathFunctions.booleanToInt(false)).isEqualTo(0);
  }

  @Test
  public void testBooleanToLong() {
    assertThat(MathFunctions.booleanToLong(true)).isEqualTo(1);
    assertThat(MathFunctions.booleanToLong(false)).isEqualTo(0);
  }

  @Test
  public void testBooleanToDouble() {
    assertThat(MathFunctions.booleanToDouble(true)).isEqualTo(1.0);
    assertThat(MathFunctions.booleanToDouble(false)).isEqualTo(0.0);
  }

  @Test
  public void testLongToBoolean() {
    assertThat(MathFunctions.longToBoolean(0L)).isFalse();

    assertThat(MathFunctions.longToBoolean(1L)).isTrue();
    assertThat(MathFunctions.longToBoolean(3L)).isTrue();
    assertThat(MathFunctions.longToBoolean(-1L)).isTrue();
  }
}
