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

  @Test
  public void testContainsWithMapper() {
    assertThat(ArraysFunctions.<String, Integer> contains(null, Integer::valueOf, 1)).isFalse();
    assertThat(ArraysFunctions.contains(new String[0], Integer::valueOf, 1)).isFalse();

    assertThat(ArraysFunctions.contains(new String[] {"0", "1", null}, Integer::valueOf, null)).isTrue();
    assertThat(ArraysFunctions.contains(new String[] {"0", "1", "2"}, Integer::valueOf, 1)).isTrue();
  }

  @Test
  public void testMap() {
    assertThat(ArraysFunctions.<String, Integer> map(null, Integer::valueOf, Integer.class)).isNull();
    assertThat(ArraysFunctions.map(new String[0], Integer::valueOf, Integer.class)).isEmpty();
    assertThat(ArraysFunctions.map(new String[] {"1", "2", "3"}, Integer::valueOf, Integer.class)).contains(1, 2, 3);
  }
}
