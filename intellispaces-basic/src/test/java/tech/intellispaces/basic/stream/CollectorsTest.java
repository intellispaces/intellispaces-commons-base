package tech.intellispaces.basic.stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.intellispaces.basic.collection.CollectionFunctions;
import tech.intellispaces.basic.exception.UnexpectedException;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link Collectors}.
 */
public class CollectorsTest {

  @Test
  public void testOne() {
    assertThatThrownBy(() -> Stream.of().collect(Collectors.one()))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Expected stream with one element");

    assertThat(Stream.of("a").collect(Collectors.one())).isEqualTo("a");

    assertThatThrownBy(() -> Stream.of("a", "b").collect(Collectors.one()))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Expected stream with one element");
  }

  @Test
  public void testOptional() {
    assertThat(Stream.of().collect(Collectors.optional())).isEmpty();

    assertThat(Stream.of("a").collect(Collectors.optional())).contains("a");

    assertThatThrownBy(() -> Stream.of("a", "b").collect(Collectors.optional()))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Expected stream with one element");
  }

  @Test
  public void testContainsAny_whenTwoOptions() {
    Assertions.assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 1, 5)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 5, 1)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 5, 6)).isFalse();

    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), null, 6)).isFalse();
  }

  @Test
  public void testContainsAny_whenThreeOptions() {
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 1, 5, 6)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 6, 5, 1)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 5, 6, 7)).isFalse();

    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), null, 6, 7)).isFalse();
  }

  @Test
  public void testContainsAny_whenFourOptions() {
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 1, 5, 6, 7)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 7, 6, 5, 1)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 5, 6, 7, 8)).isFalse();

    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), null, 6, 7, 8)).isFalse();
  }

  @Test
  public void testContainsAny_whenFiveOptions() {
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 1, 5, 6, 7, 8)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 8, 7, 6, 5, 1)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 5, 6, 7, 8, 9)).isFalse();

    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), null, 6, 7, 8, 9)).isFalse();
  }

  @Test
  public void testContainsAny_whenSizOptions() {
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 1, 5, 6, 7, 8, 9)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 9, 8, 7, 6, 5, 1)).isTrue();
    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), 5, 6, 7, 8, 9, 10)).isFalse();

    assertThat(CollectionFunctions.containsAnyOf(List.of(1, 2, 3, 4), null, 6, 7, 8, 9, 10)).isFalse();
  }
}
