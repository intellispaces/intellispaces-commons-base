package tech.intellispaces.framework.commons.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link Types}.
 */
public class TypesTest {

  @Test
  public void testOf() {
    // Given
    Type<String> stringType = Types.of(String.class);
    Type<Integer> integerType = Types.of(Integer.class);
    Type<Integer> objectType = Types.of(Object.class);

    // Then
    assertThat(stringType.superClass()).isSameAs(String.class);
    assertThat(integerType.superClass()).isSameAs(Integer.class);
    assertThat(objectType.superClass()).isSameAs(Object.class);
  }
}
