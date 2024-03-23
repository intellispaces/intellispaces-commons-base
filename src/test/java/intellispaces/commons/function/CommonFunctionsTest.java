package intellispaces.commons.function;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CommonFunctions}.
 */
public class CommonFunctionsTest {

  @Test
  public void testHandle_whenOneHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handle("abc", list::add);

    // Then
    assertThat(list).containsExactly("abc");
  }

  @Test
  public void testHandle_whenTwoHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handle("abc", list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc");
  }

  @Test
  public void testHandle_whenThreeHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handle("abc", list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFourHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handle("abc", list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFiveHandlesr() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handle("abc", list::add, list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandleEach_whenTwoValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handleEach("a", "b", list::add);

    // Then
    assertThat(list).containsExactly("a", "b");
  }

  @Test
  public void testHandleEach_whenThreeValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handleEach("a", "b", "c", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c");
  }

  @Test
  public void testHandleEach_whenFourValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handleEach("a", "b", "c", "d", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d");
  }

  @Test
  public void testHandleEach_whenFiveValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    CommonFunctions.handleEach("a", "b", "c", "d", "e", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d", "e");
  }

  @Test
  public void testCoalesce_whenSuppliers() {
    assertThat(CommonFunctions.coalesce(() -> "a", () -> "b")).isEqualTo("a");
    assertThat(CommonFunctions.coalesce(() -> null, () -> "b")).isEqualTo("b");
    assertThat(CommonFunctions.coalesce(() -> "a", () -> null)).isEqualTo("a");
    assertThat(CommonFunctions.coalesce(() -> null, () -> (String) null)).isNull();
  }

  @Test
  public void testCoalesce_whenFunctions() {
    assertThat((String) CommonFunctions.coalesce(1, n -> "a", n -> "b")).isEqualTo("a");
    assertThat((String) CommonFunctions.coalesce(1, n -> null, n -> "b")).isEqualTo("b");
    assertThat((String) CommonFunctions.coalesce(1, n -> "a", n -> null)).isEqualTo("a");
    assertThat((String) CommonFunctions.coalesce(1, n -> null, n -> null)).isNull();
  }
}
