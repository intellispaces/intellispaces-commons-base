package intellispaces.common.base.collection;

import intellispaces.common.base.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

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
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Expected stream with one element");

    assertThat(Stream.of("a").collect(Collectors.one())).isEqualTo("a");

    assertThatThrownBy(() -> Stream.of("a", "b").collect(Collectors.one()))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Expected stream with one element");
  }

  @Test
  public void testOptional() {
    assertThat(Stream.of().collect(Collectors.optional())).isEmpty();

    assertThat(Stream.of("a").collect(Collectors.optional())).contains("a");

    assertThatThrownBy(() -> Stream.of("a", "b").collect(Collectors.optional()))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Expected stream with one element");
  }
}
