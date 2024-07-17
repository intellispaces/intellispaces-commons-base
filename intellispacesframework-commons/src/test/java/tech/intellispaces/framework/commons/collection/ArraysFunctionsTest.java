package tech.intellispaces.framework.commons.collection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link ArraysFunctions}.
 */
public class ArraysFunctionsTest {

  @Test
  public void testContains() {
    assertThat(ArraysFunctions.contains(null, "")).isFalse();
    assertThat(ArraysFunctions.contains(new String[] {}, "")).isFalse();

    assertThat(ArraysFunctions.contains(new String[] {"a", "b", "c"}, null)).isFalse();
    assertThat(ArraysFunctions.contains(new String[] {"a", null, "c"}, null)).isTrue();

    assertThat(ArraysFunctions.contains(new String[] {"a", "b", "c"}, "c")).isTrue();
  }
}
