package tech.intellispaces.commons.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link ArraysFunctions}.
 */
public class ArraysFunctionsTest {

  @Test
  public void testIsNullOrEmpty() {
    assertThat(ArraysFunctions.isNullOrEmpty(null)).isTrue();
    assertThat(ArraysFunctions.isNullOrEmpty(new String[0])).isTrue();
    assertThat(ArraysFunctions.isNullOrEmpty(new String[1])).isFalse();
  }

  @Test
  public void testJoin_whenString() {
    assertThat(ArraysFunctions.join(null, (String[]) null)).isNull();
    assertThat(ArraysFunctions.join(null, "a", "b")).containsExactly("a", "b");
    assertThat(ArraysFunctions.join(new String[] { "a", "b" })).containsExactly("a", "b");
    assertThat(ArraysFunctions.join(new String[] { "a", "b" }, (String[]) null)).containsExactly("a", "b");

    assertThat(ArraysFunctions.join(new String[] { "a", "b" }, "c", "d", "e")).containsExactly("a", "b", "c", "d", "e");
  }

  @Test
  public void testToByteArray() {
    assertThat(ArraysFunctions.toByteArray(null)).isNull();
    assertThat(ArraysFunctions.toByteArray(List.of((byte) 1))).contains(1);
    assertThat(ArraysFunctions.toByteArray(List.of((byte) 1, (byte) 2))).contains(1, 2);
  }

  @Test
  public void testToByteList() {
    assertThat(ArraysFunctions.toByteList(null)).isNull();
    assertThat(ArraysFunctions.toByteList(new byte[] { 1 })).contains((byte) 1);
    assertThat(ArraysFunctions.toByteList(new byte[] { 1, 2 })).contains((byte) 1, (byte) 2);
  }

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
  public void testContainsAny() {
    assertThat(ArraysFunctions.containsAny(null, "a", "b")).isFalse();
    assertThat(ArraysFunctions.containsAny(new String[] {}, "a", "b")).isFalse();

    assertThat(ArraysFunctions.containsAny(new String[] {"a", "b", "c"}, null, null)).isFalse();
    assertThat(ArraysFunctions.containsAny(new String[] {"a", null, "c"}, null, "d")).isTrue();
    assertThat(ArraysFunctions.containsAny(new String[] {"a", "b", "c"}, "c", "d")).isTrue();
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

  @Test
  public void testArrayToInputStream_whenBytes() {
    assertThat(ArraysFunctions.arrayToInputStream(null)).isNull();
    assertThat(ArraysFunctions.arrayToInputStream(new byte[0])).isEmpty();
    assertThat(ArraysFunctions.arrayToInputStream(new byte[] { 1, 2, 3 })).hasBinaryContent(new byte[] { 1, 2, 3 });
  }
}
