package intellispaces.common.utils.collection;

import intellispaces.common.utils.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link AdditionalCollectors}.
 */
public class AdditionalCollectorsTest {

  @Test
  public void testOne() {
    assertThatThrownBy(() -> Stream.of().collect(AdditionalCollectors.one()))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Expected stream with one element");

    assertThat(Stream.of("a").collect(AdditionalCollectors.one())).isEqualTo("a");

    assertThatThrownBy(() -> Stream.of("a", "b").collect(AdditionalCollectors.one()))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Expected stream with one element");
  }

  @Test
  public void testOptional() {
    assertThat(Stream.of().collect(AdditionalCollectors.optional())).isEmpty();

    assertThat(Stream.of("a").collect(AdditionalCollectors.optional())).contains("a");

    assertThatThrownBy(() -> Stream.of("a", "b").collect(AdditionalCollectors.optional()))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Expected stream with one element");
  }
}
