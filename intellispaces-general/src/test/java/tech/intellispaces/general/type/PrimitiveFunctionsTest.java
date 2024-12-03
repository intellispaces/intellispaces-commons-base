package tech.intellispaces.general.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link PrimitiveFunctions} class.
 */
public class PrimitiveFunctionsTest {

  @Test
  public void testBooleanToInt() {
    assertThat(PrimitiveFunctions.booleanToInt(true)).isEqualTo(1);
    assertThat(PrimitiveFunctions.booleanToInt(false)).isEqualTo(0);
  }

  @Test
  public void testBooleanToLong() {
    assertThat(PrimitiveFunctions.booleanToLong(true)).isEqualTo(1);
    assertThat(PrimitiveFunctions.booleanToLong(false)).isEqualTo(0);
  }

  @Test
  public void testBooleanToDouble() {
    assertThat(PrimitiveFunctions.booleanToDouble(true)).isEqualTo(1.0);
    assertThat(PrimitiveFunctions.booleanToDouble(false)).isEqualTo(0.0);
  }

  @Test
  public void testBooleanToString() {
    assertThat(PrimitiveFunctions.booleanToString(true)).isEqualTo("true");
    assertThat(PrimitiveFunctions.booleanToString(false)).isEqualTo("false");
  }

  @Test
  public void testLongToBoolean() {
    assertThat(PrimitiveFunctions.longToBoolean(0L)).isFalse();

    assertThat(PrimitiveFunctions.longToBoolean(1L)).isTrue();
    assertThat(PrimitiveFunctions.longToBoolean(3L)).isTrue();
    assertThat(PrimitiveFunctions.longToBoolean(-1L)).isTrue();
  }
}
