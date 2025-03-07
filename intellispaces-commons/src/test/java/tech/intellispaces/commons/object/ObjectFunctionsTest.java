package tech.intellispaces.commons.object;

import org.junit.jupiter.api.Test;
import tech.intellispaces.commons.exception.UnexpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link ObjectFunctions}.
 */
public class ObjectFunctionsTest {

  @Test
  public void testCastToInt() {
    assertThat(ObjectFunctions.castToInt('a')).isEqualTo(97);
    assertThat(ObjectFunctions.castToInt((byte) 13)).isEqualTo(13);
    assertThat(ObjectFunctions.castToInt((short) 13)).isEqualTo(13);
    assertThat(ObjectFunctions.castToInt((13))).isEqualTo(13);

    assertThatThrownBy(() -> ObjectFunctions.castToInt(13L))
        .isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ObjectFunctions.castToInt(3.14F))
        .isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ObjectFunctions.castToInt(3.14D))
        .isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ObjectFunctions.castToInt("String"))
        .isExactlyInstanceOf(UnexpectedException.class);
  }

  @Test
  public void testCastToDouble() {
    assertThat(ObjectFunctions.castToDouble('a')).isEqualTo(97.0);
    assertThat(ObjectFunctions.castToDouble((byte) 13)).isEqualTo(13.0);
    assertThat(ObjectFunctions.castToDouble((short) 13)).isEqualTo(13.0);
    assertThat(ObjectFunctions.castToDouble((13))).isEqualTo(13.0);
    assertThat(ObjectFunctions.castToDouble(13L)).isEqualTo(13.0);
    assertThat(ObjectFunctions.castToDouble(13F)).isEqualTo(13.0);
    assertThat(ObjectFunctions.castToDouble(13.4D)).isEqualTo(13.4);

    assertThatThrownBy(() -> ObjectFunctions.castToDouble("String"))
        .isExactlyInstanceOf(UnexpectedException.class);
  }

  @Test
  public void testEqualsAnyOf_whenTwoOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 3)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenThreeOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenFourOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3, 4)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3, 4)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1, 4)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenFiveOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3, 4, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3, 4, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1, 4, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 1, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenSixOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3, 4, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3, 4, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1, 4, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 1, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 1, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6, 7)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6, 7)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6, null)).isTrue();
  }

  @Test
  public void testHandle_whenOneHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add);

    // Then
    assertThat(list).containsExactly("abc");
  }

  @Test
  public void testHandle_whenTwoHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc");
  }

  @Test
  public void testHandle_whenThreeHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFourHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFiveHandlesr() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandleEach_whenTwoValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", list::add);

    // Then
    assertThat(list).containsExactly("a", "b");
  }

  @Test
  public void testHandleEach_whenThreeValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", "c", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c");
  }

  @Test
  public void testHandleEach_whenFourValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", "c", "d", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d");
  }

  @Test
  public void testHandleEach_whenFiveValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", "c", "d", "e", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d", "e");
  }

  @Test
  public void testCoalesce_whenSuppliers() {
    assertThat(ObjectFunctions.coalesce(() -> "a", () -> "b")).isEqualTo("a");
    assertThat(ObjectFunctions.coalesce(() -> null, () -> "b")).isEqualTo("b");
    assertThat(ObjectFunctions.coalesce(() -> "a", () -> null)).isEqualTo("a");
    assertThat(ObjectFunctions.coalesce(() -> null, () -> (String) null)).isNull();
  }

  @Test
  public void testCoalesce_whenFunctions() {
    assertThat((String) ObjectFunctions.coalesce(1, n -> "a", n -> "b")).isEqualTo("a");
    assertThat((String) ObjectFunctions.coalesce(1, n -> null, n -> "b")).isEqualTo("b");
    assertThat((String) ObjectFunctions.coalesce(1, n -> "a", n -> null)).isEqualTo("a");
    assertThat((String) ObjectFunctions.coalesce(1, n -> null, n -> null)).isNull();
  }
}
