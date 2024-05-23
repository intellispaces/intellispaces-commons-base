package tech.intellispacesframework.commons.action;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ActionBuilders}.
 */
public class ActionBuildersTest {

  @Test
  public void testAction_whenRunnable() {
    // Given
    List<Integer> values = new ArrayList<>();
    Runnable runnable = () -> values.add(values.size() + 1);
    Action action = ActionBuilders.action(runnable);

    // When
    action.execute();
    action.execute();
    action.execute();

    // Then
    assertThat(values).containsExactly(1, 2, 3);
  }

  @Test
  public void testAction_whenRunnable_andFirstTimeOnly() {
    // Given
    List<Integer> values = new ArrayList<>();
    Runnable runnable = () -> values.add(values.size() + 1);
    Action action = ActionBuilders.action(runnable).firstTimeOnly();

    // When
    action.execute();
    action.execute();
    action.execute();

    // Then
    assertThat(values).containsExactly(1);
  }

  @Test
  public void testAction_whenRunnable_andNotFirstTimeOnly() {
    // Given
    List<Integer> values = new ArrayList<>();
    Runnable runnable = () -> values.add(values.size() + 1);
    Action action = ActionBuilders.action(runnable).notFirstTimeOnly();

    // When
    action.execute();
    action.execute();
    action.execute();

    // Then
    assertThat(values).containsExactly(1, 2);
  }

  @Test
  public void testAction_whenConsumer() {
    // Given
    List<Integer> values = new ArrayList<>();
    Action action = ActionBuilders.action(values::add, 1);

    // When
    action.execute();
    action.execute();
    action.execute();

    // Then
    assertThat(values).containsExactly(1, 1, 1);
  }

  @Test
  public void testAction_whenJoin() {
    // Given
    List<Integer> values = new ArrayList<>();
    Action action = ActionBuilders.action(values::add, 1)
        .join(ActionBuilders.action(values::add, 2));

    // When
    action.execute();
    action.execute();
    action.execute();

    // Then
    assertThat(values).containsExactly(1, 2, 1, 2, 1, 2);
  }

  @Test
  public void testResettableGetter_whenNoInitValue() {
    ResettableGetter<String> getter = ActionBuilders.resettableGetter();
    assertThat(getter.get()).isNull();

    getter.set("value1");
    assertThat(getter.get()).isEqualTo("value1");

    getter.set("value2");
    assertThat(getter.get()).isEqualTo("value2");
  }

  @Test
  public void testResettableGetter_whenInitValue() {
    ResettableGetter<String> getter = ActionBuilders.resettableGetter("value1");
    assertThat(getter.get()).isEqualTo("value1");

    getter.set("value2");
    assertThat(getter.get()).isEqualTo("value2");

    getter.set("value3");
    assertThat(getter.get()).isEqualTo("value3");
  }

  @Test
  public void testCachedLazyGetter_whenSupplier() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionBuilders.cachedLazyGetter(deque::pollFirst);
    String result1 = getterAction.get();
    String result2 = getterAction.get();
    String result3 = getterAction.get();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionBuilders.cachedLazyGetter(v -> deque.pollFirst(), 1);
    String result1 = getterAction.get();
    String result2 = getterAction.get();
    String result3 = getterAction.get();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenBiFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionBuilders.cachedLazyGetter((v1, v2) -> deque.pollFirst(), 1, 2);
    String result1 = getterAction.get();
    String result2 = getterAction.get();
    String result3 = getterAction.get();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenTriFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionBuilders.cachedLazyGetter((v1, v2, v3) -> deque.pollFirst(), 1, 2, 3);
    String result1 = getterAction.get();
    String result2 = getterAction.get();
    String result3 = getterAction.get();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }

  @Test
  public void testCachedLazyGetter_whenQuadFunction() {
    // Given
    Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

    // When
    Getter<String> getterAction = ActionBuilders.cachedLazyGetter((v1, v2, v3, v4) -> deque.pollFirst(), 1, 2, 3, 4);
    String result1 = getterAction.get();
    String result2 = getterAction.get();
    String result3 = getterAction.get();

    // Then
    assertThat(result1).isEqualTo(result2).isEqualTo(result3).isEqualTo("a");
    assertThat(deque).containsExactly("b", "c");
  }
}
