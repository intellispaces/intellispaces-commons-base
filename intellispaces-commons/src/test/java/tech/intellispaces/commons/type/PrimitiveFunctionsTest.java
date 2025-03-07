package tech.intellispaces.commons.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link PrimitiveFunctions} class.
 */
public class PrimitiveFunctionsTest {

  @Test
  public void testIsPrimitiveTypename() {
    assertThat(PrimitiveFunctions.isPrimitiveTypename(null)).isFalse();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("")).isFalse();

    assertThat(PrimitiveFunctions.isPrimitiveTypename("boolean")).isTrue();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("char")).isTrue();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("byte")).isTrue();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("short")).isTrue();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("int")).isTrue();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("long")).isTrue();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("float")).isTrue();
    assertThat(PrimitiveFunctions.isPrimitiveTypename("double")).isTrue();

    assertThat(PrimitiveFunctions.isPrimitiveTypename(Boolean.class.getName())).isFalse();
    assertThat(PrimitiveFunctions.isPrimitiveTypename(Boolean.class.getCanonicalName())).isFalse();
  }

  @Test
  public void testIntToByte() {
    assertThat(PrimitiveFunctions.intToByte(0)).isEqualTo((byte) 0);
    assertThat(PrimitiveFunctions.intToByte(1)).isEqualTo((byte) 1);
    assertThat(PrimitiveFunctions.intToByte(-1)).isEqualTo((byte) -1);
  }

  @Test
  public void testIntToBoolean() {
    assertThat(PrimitiveFunctions.intToBoolean(0)).isFalse();
    assertThat(PrimitiveFunctions.intToBoolean(1)).isTrue();
    assertThat(PrimitiveFunctions.intToBoolean(-1)).isFalse();
  }

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
