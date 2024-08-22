package intellispaces.commons.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link ArraysFunctions}.
 */
public class ArraysFunctionsTest {

  @Test
  public void testForeach() {
    // Given
    List<String> list = new ArrayList<>();
    String[] array = new String[] {"a", "b", "c"};

    // When
    ArraysFunctions.foreach(array, list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c");
  }

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

  @Test
  public void testWrap_whenBoolean() {
    assertThat(ArraysFunctions.wrap((boolean[]) null)).isNull();
    assertThat(ArraysFunctions.wrap(new boolean[] { true })).containsExactly(Boolean.TRUE);
    assertThat(ArraysFunctions.wrap(new boolean[] { true, false })).containsExactly(Boolean.TRUE, Boolean.FALSE);
  }

  @Test
  public void testWrap_whenInteger() {
    assertThat(ArraysFunctions.wrap((int[]) null)).isNull();
    assertThat(ArraysFunctions.wrap(new int[] { 1 })).containsExactly(1);
    assertThat(ArraysFunctions.wrap(new int[] { 1, 2 })).containsExactly(1, 2);
  }

  @Test
  public void testWrap_whenDouble() {
    assertThat(ArraysFunctions.wrap((double[]) null)).isNull();
    assertThat(ArraysFunctions.wrap(new double[] { 1.1 })).containsExactly(1.1);
    assertThat(ArraysFunctions.wrap(new double[] { 1.1, 2.2 })).containsExactly(1.1, 2.2);
  }
}
