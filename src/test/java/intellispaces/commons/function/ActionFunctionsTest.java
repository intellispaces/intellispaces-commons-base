package intellispaces.commons.function;

import intellispaces.commons.model.action.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ActionFunctions}.
 */
public class ActionFunctionsTest {


  @Test
  public void testCachedLazyGetter_whenSupplier() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionFunctions.cachedLazyGetter(deque::pollFirst);
    String result1 = getterAction.execute();
    String result2 = getterAction.get();
    String result3 = getterAction.execute();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionFunctions.cachedLazyGetter(v -> deque.pollFirst(), 1);
    String result1 = getterAction.execute();
    String result2 = getterAction.get();
    String result3 = getterAction.execute();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenBiFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionFunctions.cachedLazyGetter((v1, v2) -> deque.pollFirst(), 1, 2);
    String result1 = getterAction.execute();
    String result2 = getterAction.get();
    String result3 = getterAction.execute();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenTriFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionFunctions.cachedLazyGetter((v1, v2, v3) -> deque.pollFirst(), 1, 2, 3);
    String result1 = getterAction.execute();
    String result2 = getterAction.get();
    String result3 = getterAction.execute();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenQuadFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionFunctions.cachedLazyGetter((v1, v2, v3, v4) -> deque.pollFirst(), 1, 2, 3, 4);
    String result1 = getterAction.execute();
    String result2 = getterAction.get();
    String result3 = getterAction.execute();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }
}
