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
  public void testHandle_whenTwoHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc");
  }

  @Test
  public void testHandle_whenThreeHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFourHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFiveHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ActionFunctions.handle("abc", list::add, list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc", "abc");
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
}
