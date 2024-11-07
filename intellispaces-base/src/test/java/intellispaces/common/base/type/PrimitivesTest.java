package intellispaces.common.base.type;

import intellispaces.common.base.exception.UnexpectedException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link Primitives} class.
 */
public class PrimitivesTest {

  @Test
  public void testGet() {
    assertThat(Primitives.get("boolean")).isSameAs(Primitives.Boolean);
    assertThat(Primitives.get("char")).isSameAs(Primitives.Char);
    assertThat(Primitives.get("byte")).isSameAs(Primitives.Byte);
    assertThat(Primitives.get("short")).isSameAs(Primitives.Short);
    assertThat(Primitives.get("int")).isSameAs(Primitives.Int);
    assertThat(Primitives.get("long")).isSameAs(Primitives.Long);
    assertThat(Primitives.get("float")).isSameAs(Primitives.Float);
    assertThat(Primitives.get("double")).isSameAs(Primitives.Double);

    assertThatThrownBy(() -> Primitives.get("String"))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Not primitive typename: String");
  }

  @Test
  public void testIsBoolean() {
    assertThat(Primitives.Boolean.isBoolean()).isTrue();
    assertThat(Primitives.Char.isBoolean()).isFalse();
    assertThat(Primitives.Byte.isBoolean()).isFalse();
    assertThat(Primitives.Short.isBoolean()).isFalse();
    assertThat(Primitives.Int.isBoolean()).isFalse();
    assertThat(Primitives.Long.isBoolean()).isFalse();
    assertThat(Primitives.Float.isBoolean()).isFalse();
    assertThat(Primitives.Double.isBoolean()).isFalse();
  }

  @Test
  public void testIsChar() {
    assertThat(Primitives.Boolean.isChar()).isFalse();
    assertThat(Primitives.Char.isChar()).isTrue();
    assertThat(Primitives.Byte.isChar()).isFalse();
    assertThat(Primitives.Short.isChar()).isFalse();
    assertThat(Primitives.Int.isChar()).isFalse();
    assertThat(Primitives.Long.isChar()).isFalse();
    assertThat(Primitives.Float.isChar()).isFalse();
    assertThat(Primitives.Double.isChar()).isFalse();
  }

  @Test
  public void testIsByte() {
    assertThat(Primitives.Boolean.isByte()).isFalse();
    assertThat(Primitives.Char.isByte()).isFalse();
    assertThat(Primitives.Byte.isByte()).isTrue();
    assertThat(Primitives.Short.isByte()).isFalse();
    assertThat(Primitives.Int.isByte()).isFalse();
    assertThat(Primitives.Long.isByte()).isFalse();
    assertThat(Primitives.Float.isByte()).isFalse();
    assertThat(Primitives.Double.isByte()).isFalse();
  }

  @Test
  public void testIsShort() {
    assertThat(Primitives.Boolean.isShort()).isFalse();
    assertThat(Primitives.Char.isShort()).isFalse();
    assertThat(Primitives.Byte.isShort()).isFalse();
    assertThat(Primitives.Short.isShort()).isTrue();
    assertThat(Primitives.Int.isShort()).isFalse();
    assertThat(Primitives.Long.isShort()).isFalse();
    assertThat(Primitives.Float.isShort()).isFalse();
    assertThat(Primitives.Double.isShort()).isFalse();
  }

  @Test
  public void testIsInt() {
    assertThat(Primitives.Boolean.isInt()).isFalse();
    assertThat(Primitives.Char.isInt()).isFalse();
    assertThat(Primitives.Byte.isInt()).isFalse();
    assertThat(Primitives.Short.isInt()).isFalse();
    assertThat(Primitives.Int.isInt()).isTrue();
    assertThat(Primitives.Long.isInt()).isFalse();
    assertThat(Primitives.Float.isInt()).isFalse();
    assertThat(Primitives.Double.isInt()).isFalse();
  }

  @Test
  public void testIsLong() {
    assertThat(Primitives.Boolean.isLong()).isFalse();
    assertThat(Primitives.Char.isLong()).isFalse();
    assertThat(Primitives.Byte.isLong()).isFalse();
    assertThat(Primitives.Short.isLong()).isFalse();
    assertThat(Primitives.Int.isLong()).isFalse();
    assertThat(Primitives.Long.isLong()).isTrue();
    assertThat(Primitives.Float.isLong()).isFalse();
    assertThat(Primitives.Double.isLong()).isFalse();
  }

  @Test
  public void testIsFloat() {
    assertThat(Primitives.Boolean.isFloat()).isFalse();
    assertThat(Primitives.Char.isFloat()).isFalse();
    assertThat(Primitives.Byte.isFloat()).isFalse();
    assertThat(Primitives.Short.isFloat()).isFalse();
    assertThat(Primitives.Int.isFloat()).isFalse();
    assertThat(Primitives.Long.isFloat()).isFalse();
    assertThat(Primitives.Float.isFloat()).isTrue();
    assertThat(Primitives.Double.isFloat()).isFalse();
  }

  @Test
  public void testIsDouble() {
    assertThat(Primitives.Boolean.isDouble()).isFalse();
    assertThat(Primitives.Char.isDouble()).isFalse();
    assertThat(Primitives.Byte.isDouble()).isFalse();
    assertThat(Primitives.Short.isDouble()).isFalse();
    assertThat(Primitives.Int.isDouble()).isFalse();
    assertThat(Primitives.Long.isDouble()).isFalse();
    assertThat(Primitives.Float.isDouble()).isFalse();
    assertThat(Primitives.Double.isDouble()).isTrue();
  }

  @Test
  public void testTypename() {
    assertThat(Primitives.Boolean.typename()).isEqualTo("boolean");
    assertThat(Primitives.Char.typename()).isEqualTo("char");
    assertThat(Primitives.Byte.typename()).isEqualTo("byte");
    assertThat(Primitives.Short.typename()).isEqualTo("short");
    assertThat(Primitives.Int.typename()).isEqualTo("int");
    assertThat(Primitives.Long.typename()).isEqualTo("long");
    assertThat(Primitives.Float.typename()).isEqualTo("float");
    assertThat(Primitives.Double.typename()).isEqualTo("double");
  }
}
