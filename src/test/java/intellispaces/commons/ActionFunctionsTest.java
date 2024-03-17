package intellispaces.commons;

import intellispaces.commons.action.GetterAction;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ActionFunctions}.
 */
public class ActionFunctionsTest {

  @Test
  public void testHandle_whenOneHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add);

    // Then
    assertThat(list).containsExactly("abc");
  }

  @Test
  public void testHandle_whenTwoHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc");
  }

  @Test
  public void testHandle_whenThreeHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFourHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFiveHandlesr() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandleEach_whenTwoValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handleEach("a", "b", list::add);

    // Then
    assertThat(list).containsExactly("a", "b");
  }

  @Test
  public void testHandleEach_whenThreeValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handleEach("a", "b", "c", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c");
  }

  @Test
  public void testHandleEach_whenFourValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handleEach("a", "b", "c", "d", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d");
  }

  @Test
  public void testHandleEach_whenFiveValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handleEach("a", "b", "c", "d", "e", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d", "e");
  }

  @Test
  public void testCoalesce_whenSuppliers() {
    assertThat(ActionFunctions.coalesce(() -> "a", () -> "b")).isEqualTo("a");
    assertThat(ActionFunctions.coalesce(() -> null, () -> "b")).isEqualTo("b");
    assertThat(ActionFunctions.coalesce(() -> "a", () -> null)).isEqualTo("a");
    assertThat(ActionFunctions.coalesce(() -> null, () -> (String) null)).isNull();
  }

  @Test
  public void testCoalesce_whenFunctions() {
    assertThat((String) ActionFunctions.coalesce(1, n -> "a", n -> "b")).isEqualTo("a");
    assertThat((String) ActionFunctions.coalesce(1, n -> null, n -> "b")).isEqualTo("b");
    assertThat((String) ActionFunctions.coalesce(1, n -> "a", n -> null)).isEqualTo("a");
    assertThat((String) ActionFunctions.coalesce(1, n -> null, n -> null)).isNull();
  }

  @Test
  public void testCachedLazyGetter_whenSupplier() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    GetterAction<String> getter = ActionFunctions.cachedLazyGetter(deque::pollFirst);
    String result1 = getter.perform();
    String result2 = getter.get();
    String result3 = getter.perform();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    GetterAction<String> getter = ActionFunctions.cachedLazyGetter(v -> deque.pollFirst(), 1);
    String result1 = getter.perform();
    String result2 = getter.get();
    String result3 = getter.perform();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenBiFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    GetterAction<String> getter = ActionFunctions.cachedLazyGetter((v1, v2) -> deque.pollFirst(), 1, 2);
    String result1 = getter.perform();
    String result2 = getter.get();
    String result3 = getter.perform();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenTriFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    GetterAction<String> getter = ActionFunctions.cachedLazyGetter((v1, v2, v3) -> deque.pollFirst(), 1, 2, 3);
    String result1 = getter.perform();
    String result2 = getter.get();
    String result3 = getter.perform();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenQuadFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    GetterAction<String> getter = ActionFunctions.cachedLazyGetter((v1, v2, v3, v4) -> deque.pollFirst(), 1, 2, 3, 4);
    String result1 = getter.perform();
    String result2 = getter.get();
    String result3 = getter.perform();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }
}
