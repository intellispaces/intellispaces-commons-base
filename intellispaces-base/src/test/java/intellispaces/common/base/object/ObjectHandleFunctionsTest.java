package intellispaces.common.base.object;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjectHandleFunctions}.
 */
public class ObjectHandleFunctionsTest {

  @Test
  public void testHandle_whenOneHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handle("abc", list::add);

    // Then
    assertThat(list).containsExactly("abc");
  }

  @Test
  public void testHandle_whenTwoHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handle("abc", list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc");
  }

  @Test
  public void testHandle_whenThreeHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handle("abc", list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFourHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handle("abc", list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFiveHandlesr() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handle("abc", list::add, list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandleEach_whenTwoValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handleEach("a", "b", list::add);

    // Then
    assertThat(list).containsExactly("a", "b");
  }

  @Test
  public void testHandleEach_whenThreeValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handleEach("a", "b", "c", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c");
  }

  @Test
  public void testHandleEach_whenFourValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handleEach("a", "b", "c", "d", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d");
  }

  @Test
  public void testHandleEach_whenFiveValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectHandleFunctions.handleEach("a", "b", "c", "d", "e", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d", "e");
  }

  @Test
  public void testCoalesce_whenSuppliers() {
    assertThat(ObjectHandleFunctions.coalesce(() -> "a", () -> "b")).isEqualTo("a");
    assertThat(ObjectHandleFunctions.coalesce(() -> null, () -> "b")).isEqualTo("b");
    assertThat(ObjectHandleFunctions.coalesce(() -> "a", () -> null)).isEqualTo("a");
    assertThat(ObjectHandleFunctions.coalesce(() -> null, () -> (String) null)).isNull();
  }

  @Test
  public void testCoalesce_whenFunctions() {
    assertThat((String) ObjectHandleFunctions.coalesce(1, n -> "a", n -> "b")).isEqualTo("a");
    assertThat((String) ObjectHandleFunctions.coalesce(1, n -> null, n -> "b")).isEqualTo("b");
    assertThat((String) ObjectHandleFunctions.coalesce(1, n -> "a", n -> null)).isEqualTo("a");
    assertThat((String) ObjectHandleFunctions.coalesce(1, n -> null, n -> null)).isNull();
  }
}
