package tech.intellispaces.entity.type;

import org.junit.jupiter.api.Test;
import tech.intellispaces.entity.exception.UnexpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link PrimitiveTypes} class.
 */
public class PrimitiveTypesTest {

  @Test
  public void testGet() {
    assertThat(PrimitiveTypes.get("boolean")).isSameAs(PrimitiveTypes.Boolean);
    assertThat(PrimitiveTypes.get("char")).isSameAs(PrimitiveTypes.Char);
    assertThat(PrimitiveTypes.get("byte")).isSameAs(PrimitiveTypes.Byte);
    assertThat(PrimitiveTypes.get("short")).isSameAs(PrimitiveTypes.Short);
    assertThat(PrimitiveTypes.get("int")).isSameAs(PrimitiveTypes.Int);
    assertThat(PrimitiveTypes.get("long")).isSameAs(PrimitiveTypes.Long);
    assertThat(PrimitiveTypes.get("float")).isSameAs(PrimitiveTypes.Float);
    assertThat(PrimitiveTypes.get("double")).isSameAs(PrimitiveTypes.Double);

    assertThatThrownBy(() -> PrimitiveTypes.get("String"))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Not primitive typename: String");
  }

  @Test
  public void testIs_byName_whenBoolean() {
    assertThat(PrimitiveTypes.Boolean.is(null)).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("")).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("Boolean")).isTrue();
    assertThat(PrimitiveTypes.Boolean.is("Char")).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("Byte")).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("Short")).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("Int")).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("Long")).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("Float")).isFalse();
    assertThat(PrimitiveTypes.Boolean.is("Double")).isFalse();
  }

  @Test
  public void testIs_byName_whenChar() {
    assertThat(PrimitiveTypes.Char.is(null)).isFalse();
    assertThat(PrimitiveTypes.Char.is("")).isFalse();
    assertThat(PrimitiveTypes.Char.is("Boolean")).isFalse();
    assertThat(PrimitiveTypes.Char.is("Char")).isTrue();
    assertThat(PrimitiveTypes.Char.is("Byte")).isFalse();
    assertThat(PrimitiveTypes.Char.is("Short")).isFalse();
    assertThat(PrimitiveTypes.Char.is("Int")).isFalse();
    assertThat(PrimitiveTypes.Char.is("Long")).isFalse();
    assertThat(PrimitiveTypes.Char.is("Float")).isFalse();
    assertThat(PrimitiveTypes.Char.is("Double")).isFalse();
  }

  @Test
  public void testIs_byName_whenByte() {
    assertThat(PrimitiveTypes.Byte.is(null)).isFalse();
    assertThat(PrimitiveTypes.Byte.is("")).isFalse();
    assertThat(PrimitiveTypes.Byte.is("Boolean")).isFalse();
    assertThat(PrimitiveTypes.Byte.is("Char")).isFalse();
    assertThat(PrimitiveTypes.Byte.is("Byte")).isTrue();
    assertThat(PrimitiveTypes.Byte.is("Short")).isFalse();
    assertThat(PrimitiveTypes.Byte.is("Int")).isFalse();
    assertThat(PrimitiveTypes.Byte.is("Long")).isFalse();
    assertThat(PrimitiveTypes.Byte.is("Float")).isFalse();
    assertThat(PrimitiveTypes.Byte.is("Double")).isFalse();
  }

  @Test
  public void testIs_byName_whenShort() {
    assertThat(PrimitiveTypes.Short.is(null)).isFalse();
    assertThat(PrimitiveTypes.Short.is("")).isFalse();
    assertThat(PrimitiveTypes.Short.is("Boolean")).isFalse();
    assertThat(PrimitiveTypes.Short.is("Char")).isFalse();
    assertThat(PrimitiveTypes.Short.is("Byte")).isFalse();
    assertThat(PrimitiveTypes.Short.is("Short")).isTrue();
    assertThat(PrimitiveTypes.Short.is("Int")).isFalse();
    assertThat(PrimitiveTypes.Short.is("Long")).isFalse();
    assertThat(PrimitiveTypes.Short.is("Float")).isFalse();
    assertThat(PrimitiveTypes.Short.is("Double")).isFalse();
  }

  @Test
  public void testIs_byName_whenInt() {
    assertThat(PrimitiveTypes.Int.is(null)).isFalse();
    assertThat(PrimitiveTypes.Int.is("")).isFalse();
    assertThat(PrimitiveTypes.Int.is("Boolean")).isFalse();
    assertThat(PrimitiveTypes.Int.is("Char")).isFalse();
    assertThat(PrimitiveTypes.Int.is("Byte")).isFalse();
    assertThat(PrimitiveTypes.Int.is("Short")).isFalse();
    assertThat(PrimitiveTypes.Int.is("Int")).isTrue();
    assertThat(PrimitiveTypes.Int.is("Long")).isFalse();
    assertThat(PrimitiveTypes.Int.is("Float")).isFalse();
    assertThat(PrimitiveTypes.Int.is("Double")).isFalse();
  }

  @Test
  public void testIs_byName_whenLong() {
    assertThat(PrimitiveTypes.Long.is(null)).isFalse();
    assertThat(PrimitiveTypes.Long.is("")).isFalse();
    assertThat(PrimitiveTypes.Long.is("Boolean")).isFalse();
    assertThat(PrimitiveTypes.Long.is("Char")).isFalse();
    assertThat(PrimitiveTypes.Long.is("Byte")).isFalse();
    assertThat(PrimitiveTypes.Long.is("Short")).isFalse();
    assertThat(PrimitiveTypes.Long.is("Int")).isFalse();
    assertThat(PrimitiveTypes.Long.is("Long")).isTrue();
    assertThat(PrimitiveTypes.Long.is("Float")).isFalse();
    assertThat(PrimitiveTypes.Long.is("Double")).isFalse();
  }

  @Test
  public void testIs_byName_whenFloat() {
    assertThat(PrimitiveTypes.Float.is(null)).isFalse();
    assertThat(PrimitiveTypes.Float.is("")).isFalse();
    assertThat(PrimitiveTypes.Float.is("Boolean")).isFalse();
    assertThat(PrimitiveTypes.Float.is("Char")).isFalse();
    assertThat(PrimitiveTypes.Float.is("Byte")).isFalse();
    assertThat(PrimitiveTypes.Float.is("Short")).isFalse();
    assertThat(PrimitiveTypes.Float.is("Int")).isFalse();
    assertThat(PrimitiveTypes.Float.is("Long")).isFalse();
    assertThat(PrimitiveTypes.Float.is("Float")).isTrue();
    assertThat(PrimitiveTypes.Float.is("Double")).isFalse();
  }

  @Test
  public void testIs_byName_whenDouble() {
    assertThat(PrimitiveTypes.Double.is(null)).isFalse();
    assertThat(PrimitiveTypes.Double.is("")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Boolean")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Char")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Byte")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Short")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Int")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Long")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Float")).isFalse();
    assertThat(PrimitiveTypes.Double.is("Double")).isTrue();
  }

  @Test
  public void testIs_byOrdinal_whenBoolean() {
    assertThat(PrimitiveTypes.Boolean.is(0)).isTrue();
    assertThat(PrimitiveTypes.Boolean.is(1)).isFalse();
    assertThat(PrimitiveTypes.Boolean.is(2)).isFalse();
    assertThat(PrimitiveTypes.Boolean.is(3)).isFalse();
    assertThat(PrimitiveTypes.Boolean.is(4)).isFalse();
    assertThat(PrimitiveTypes.Boolean.is(5)).isFalse();
    assertThat(PrimitiveTypes.Boolean.is(6)).isFalse();
    assertThat(PrimitiveTypes.Boolean.is(7)).isFalse();
  }

  @Test
  public void testIs_byOrdinal_whenChar() {
    assertThat(PrimitiveTypes.Char.is(0)).isFalse();
    assertThat(PrimitiveTypes.Char.is(1)).isTrue();
    assertThat(PrimitiveTypes.Char.is(2)).isFalse();
    assertThat(PrimitiveTypes.Char.is(3)).isFalse();
    assertThat(PrimitiveTypes.Char.is(4)).isFalse();
    assertThat(PrimitiveTypes.Char.is(5)).isFalse();
    assertThat(PrimitiveTypes.Char.is(6)).isFalse();
    assertThat(PrimitiveTypes.Char.is(7)).isFalse();
  }

  @Test
  public void testIs_byOrdinal_whenByte() {
    assertThat(PrimitiveTypes.Byte.is(0)).isFalse();
    assertThat(PrimitiveTypes.Byte.is(1)).isFalse();
    assertThat(PrimitiveTypes.Byte.is(2)).isTrue();
    assertThat(PrimitiveTypes.Byte.is(3)).isFalse();
    assertThat(PrimitiveTypes.Byte.is(4)).isFalse();
    assertThat(PrimitiveTypes.Byte.is(5)).isFalse();
    assertThat(PrimitiveTypes.Byte.is(6)).isFalse();
    assertThat(PrimitiveTypes.Byte.is(7)).isFalse();
  }

  @Test
  public void testIs_byOrdinal_whenShort() {
    assertThat(PrimitiveTypes.Short.is(0)).isFalse();
    assertThat(PrimitiveTypes.Short.is(1)).isFalse();
    assertThat(PrimitiveTypes.Short.is(2)).isFalse();
    assertThat(PrimitiveTypes.Short.is(3)).isTrue();
    assertThat(PrimitiveTypes.Short.is(4)).isFalse();
    assertThat(PrimitiveTypes.Short.is(5)).isFalse();
    assertThat(PrimitiveTypes.Short.is(6)).isFalse();
    assertThat(PrimitiveTypes.Short.is(7)).isFalse();
  }

  @Test
  public void testIs_byOrdinal_whenInt() {
    assertThat(PrimitiveTypes.Int.is(0)).isFalse();
    assertThat(PrimitiveTypes.Int.is(1)).isFalse();
    assertThat(PrimitiveTypes.Int.is(2)).isFalse();
    assertThat(PrimitiveTypes.Int.is(3)).isFalse();
    assertThat(PrimitiveTypes.Int.is(4)).isTrue();
    assertThat(PrimitiveTypes.Int.is(5)).isFalse();
    assertThat(PrimitiveTypes.Int.is(6)).isFalse();
    assertThat(PrimitiveTypes.Int.is(7)).isFalse();
  }

  @Test
  public void testIs_byOrdinal_whenLong() {
    assertThat(PrimitiveTypes.Long.is(0)).isFalse();
    assertThat(PrimitiveTypes.Long.is(1)).isFalse();
    assertThat(PrimitiveTypes.Long.is(2)).isFalse();
    assertThat(PrimitiveTypes.Long.is(3)).isFalse();
    assertThat(PrimitiveTypes.Long.is(4)).isFalse();
    assertThat(PrimitiveTypes.Long.is(5)).isTrue();
    assertThat(PrimitiveTypes.Long.is(6)).isFalse();
    assertThat(PrimitiveTypes.Long.is(7)).isFalse();
  }

  @Test
  public void testIs_byOrdinal_whenFloat() {
    assertThat(PrimitiveTypes.Float.is(0)).isFalse();
    assertThat(PrimitiveTypes.Float.is(1)).isFalse();
    assertThat(PrimitiveTypes.Float.is(2)).isFalse();
    assertThat(PrimitiveTypes.Float.is(3)).isFalse();
    assertThat(PrimitiveTypes.Float.is(4)).isFalse();
    assertThat(PrimitiveTypes.Float.is(5)).isFalse();
    assertThat(PrimitiveTypes.Float.is(6)).isTrue();
    assertThat(PrimitiveTypes.Float.is(7)).isFalse();
  }

  @Test
  public void testIs_byOrdinal_whenDouble() {
    assertThat(PrimitiveTypes.Double.is(0)).isFalse();
    assertThat(PrimitiveTypes.Double.is(1)).isFalse();
    assertThat(PrimitiveTypes.Double.is(2)).isFalse();
    assertThat(PrimitiveTypes.Double.is(3)).isFalse();
    assertThat(PrimitiveTypes.Double.is(4)).isFalse();
    assertThat(PrimitiveTypes.Double.is(5)).isFalse();
    assertThat(PrimitiveTypes.Double.is(6)).isFalse();
    assertThat(PrimitiveTypes.Double.is(7)).isTrue();
  }

  @Test
  public void testIsBoolean() {
    assertThat(PrimitiveTypes.Boolean.isBoolean()).isTrue();
    assertThat(PrimitiveTypes.Char.isBoolean()).isFalse();
    assertThat(PrimitiveTypes.Byte.isBoolean()).isFalse();
    assertThat(PrimitiveTypes.Short.isBoolean()).isFalse();
    assertThat(PrimitiveTypes.Int.isBoolean()).isFalse();
    assertThat(PrimitiveTypes.Long.isBoolean()).isFalse();
    assertThat(PrimitiveTypes.Float.isBoolean()).isFalse();
    assertThat(PrimitiveTypes.Double.isBoolean()).isFalse();
  }

  @Test
  public void testIsChar() {
    assertThat(PrimitiveTypes.Boolean.isChar()).isFalse();
    assertThat(PrimitiveTypes.Char.isChar()).isTrue();
    assertThat(PrimitiveTypes.Byte.isChar()).isFalse();
    assertThat(PrimitiveTypes.Short.isChar()).isFalse();
    assertThat(PrimitiveTypes.Int.isChar()).isFalse();
    assertThat(PrimitiveTypes.Long.isChar()).isFalse();
    assertThat(PrimitiveTypes.Float.isChar()).isFalse();
    assertThat(PrimitiveTypes.Double.isChar()).isFalse();
  }

  @Test
  public void testIsByte() {
    assertThat(PrimitiveTypes.Boolean.isByte()).isFalse();
    assertThat(PrimitiveTypes.Char.isByte()).isFalse();
    assertThat(PrimitiveTypes.Byte.isByte()).isTrue();
    assertThat(PrimitiveTypes.Short.isByte()).isFalse();
    assertThat(PrimitiveTypes.Int.isByte()).isFalse();
    assertThat(PrimitiveTypes.Long.isByte()).isFalse();
    assertThat(PrimitiveTypes.Float.isByte()).isFalse();
    assertThat(PrimitiveTypes.Double.isByte()).isFalse();
  }

  @Test
  public void testIsShort() {
    assertThat(PrimitiveTypes.Boolean.isShort()).isFalse();
    assertThat(PrimitiveTypes.Char.isShort()).isFalse();
    assertThat(PrimitiveTypes.Byte.isShort()).isFalse();
    assertThat(PrimitiveTypes.Short.isShort()).isTrue();
    assertThat(PrimitiveTypes.Int.isShort()).isFalse();
    assertThat(PrimitiveTypes.Long.isShort()).isFalse();
    assertThat(PrimitiveTypes.Float.isShort()).isFalse();
    assertThat(PrimitiveTypes.Double.isShort()).isFalse();
  }

  @Test
  public void testIsInt() {
    assertThat(PrimitiveTypes.Boolean.isInt()).isFalse();
    assertThat(PrimitiveTypes.Char.isInt()).isFalse();
    assertThat(PrimitiveTypes.Byte.isInt()).isFalse();
    assertThat(PrimitiveTypes.Short.isInt()).isFalse();
    assertThat(PrimitiveTypes.Int.isInt()).isTrue();
    assertThat(PrimitiveTypes.Long.isInt()).isFalse();
    assertThat(PrimitiveTypes.Float.isInt()).isFalse();
    assertThat(PrimitiveTypes.Double.isInt()).isFalse();
  }

  @Test
  public void testIsLong() {
    assertThat(PrimitiveTypes.Boolean.isLong()).isFalse();
    assertThat(PrimitiveTypes.Char.isLong()).isFalse();
    assertThat(PrimitiveTypes.Byte.isLong()).isFalse();
    assertThat(PrimitiveTypes.Short.isLong()).isFalse();
    assertThat(PrimitiveTypes.Int.isLong()).isFalse();
    assertThat(PrimitiveTypes.Long.isLong()).isTrue();
    assertThat(PrimitiveTypes.Float.isLong()).isFalse();
    assertThat(PrimitiveTypes.Double.isLong()).isFalse();
  }

  @Test
  public void testIsFloat() {
    assertThat(PrimitiveTypes.Boolean.isFloat()).isFalse();
    assertThat(PrimitiveTypes.Char.isFloat()).isFalse();
    assertThat(PrimitiveTypes.Byte.isFloat()).isFalse();
    assertThat(PrimitiveTypes.Short.isFloat()).isFalse();
    assertThat(PrimitiveTypes.Int.isFloat()).isFalse();
    assertThat(PrimitiveTypes.Long.isFloat()).isFalse();
    assertThat(PrimitiveTypes.Float.isFloat()).isTrue();
    assertThat(PrimitiveTypes.Double.isFloat()).isFalse();
  }

  @Test
  public void testIsDouble() {
    assertThat(PrimitiveTypes.Boolean.isDouble()).isFalse();
    assertThat(PrimitiveTypes.Char.isDouble()).isFalse();
    assertThat(PrimitiveTypes.Byte.isDouble()).isFalse();
    assertThat(PrimitiveTypes.Short.isDouble()).isFalse();
    assertThat(PrimitiveTypes.Int.isDouble()).isFalse();
    assertThat(PrimitiveTypes.Long.isDouble()).isFalse();
    assertThat(PrimitiveTypes.Float.isDouble()).isFalse();
    assertThat(PrimitiveTypes.Double.isDouble()).isTrue();
  }

  @Test
  public void testTypename() {
    assertThat(PrimitiveTypes.Boolean.typename()).isEqualTo("boolean");
    assertThat(PrimitiveTypes.Char.typename()).isEqualTo("char");
    assertThat(PrimitiveTypes.Byte.typename()).isEqualTo("byte");
    assertThat(PrimitiveTypes.Short.typename()).isEqualTo("short");
    assertThat(PrimitiveTypes.Int.typename()).isEqualTo("int");
    assertThat(PrimitiveTypes.Long.typename()).isEqualTo("long");
    assertThat(PrimitiveTypes.Float.typename()).isEqualTo("float");
    assertThat(PrimitiveTypes.Double.typename()).isEqualTo("double");
  }

  @Test
  public void testWrapperClass() {
    assertThat(PrimitiveTypes.Boolean.wrapperClass()).isSameAs(Boolean.class);
    assertThat(PrimitiveTypes.Char.wrapperClass()).isSameAs(Character.class);
    assertThat(PrimitiveTypes.Byte.wrapperClass()).isSameAs(Byte.class);
    assertThat(PrimitiveTypes.Short.wrapperClass()).isSameAs(Short.class);
    assertThat(PrimitiveTypes.Int.wrapperClass()).isSameAs(Integer.class);
    assertThat(PrimitiveTypes.Long.wrapperClass()).isSameAs(Long.class);
    assertThat(PrimitiveTypes.Float.wrapperClass()).isSameAs(Float.class);
    assertThat(PrimitiveTypes.Double.wrapperClass()).isSameAs(Double.class);
  }

  @Test
  public void testBaseType() {
    assertThat(PrimitiveTypes.Boolean.baseType().asClassType().baseClass()).isSameAs(boolean.class);
    assertThat(PrimitiveTypes.Char.baseType().asClassType().baseClass()).isSameAs(char.class);
    assertThat(PrimitiveTypes.Byte.baseType().asClassType().baseClass()).isSameAs(byte.class);
    assertThat(PrimitiveTypes.Short.baseType().asClassType().baseClass()).isSameAs(short.class);
    assertThat(PrimitiveTypes.Int.baseType().asClassType().baseClass()).isSameAs(int.class);
    assertThat(PrimitiveTypes.Long.baseType().asClassType().baseClass()).isSameAs(long.class);
    assertThat(PrimitiveTypes.Float.baseType().asClassType().baseClass()).isSameAs(float.class);
    assertThat(PrimitiveTypes.Double.baseType().asClassType().baseClass()).isSameAs(double.class);
  }

  @Test
  public void testBaseClass() {
    assertThat(PrimitiveTypes.Boolean.asClassType().baseClass()).isSameAs(boolean.class);
    assertThat(PrimitiveTypes.Char.asClassType().baseClass()).isSameAs(char.class);
    assertThat(PrimitiveTypes.Byte.asClassType().baseClass()).isSameAs(byte.class);
    assertThat(PrimitiveTypes.Short.asClassType().baseClass()).isSameAs(short.class);
    assertThat(PrimitiveTypes.Int.asClassType().baseClass()).isSameAs(int.class);
    assertThat(PrimitiveTypes.Long.asClassType().baseClass()).isSameAs(long.class);
    assertThat(PrimitiveTypes.Float.asClassType().baseClass()).isSameAs(float.class);
    assertThat(PrimitiveTypes.Double.asClassType().baseClass()).isSameAs(double.class);
  }

  @Test
  public void testQualifierTypes() {
    assertThat(PrimitiveTypes.Boolean.qualifierTypes()).isEmpty();
    assertThat(PrimitiveTypes.Char.qualifierTypes()).isEmpty();
    assertThat(PrimitiveTypes.Byte.qualifierTypes()).isEmpty();
    assertThat(PrimitiveTypes.Short.qualifierTypes()).isEmpty();
    assertThat(PrimitiveTypes.Int.qualifierTypes()).isEmpty();
    assertThat(PrimitiveTypes.Long.qualifierTypes()).isEmpty();
    assertThat(PrimitiveTypes.Float.qualifierTypes()).isEmpty();
    assertThat(PrimitiveTypes.Double.qualifierTypes()).isEmpty();
  }
}
