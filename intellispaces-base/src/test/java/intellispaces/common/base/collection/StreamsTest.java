package intellispaces.common.base.collection;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Streams} class.
 */
public class StreamsTest {

  @Test
  public void testGetWhenIterable() {
    // Given
    List<String> list = List.of("a", "b", "c");
    Iterable<String> iterable = List.of("a", "b", "c");

    // When
    Stream<String> stream = Streams.get(iterable);

    // Then
    assertThat(stream.toList()).isEqualTo(list);
  }
}
