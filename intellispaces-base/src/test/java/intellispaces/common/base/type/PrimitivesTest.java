package intellispaces.common.base.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    assertThat(Primitives.get("long")).isSameAs(Primitives.Long);
    assertThat(Primitives.get("int")).isSameAs(Primitives.Int);
    assertThat(Primitives.get("float")).isSameAs(Primitives.Float);
    assertThat(Primitives.get("double")).isSameAs(Primitives.Double);
  }
}
