package tech.intellispaces.entities.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link WrappedExceptions} class.
 */
public class WrappedExceptionsTest {

  @Test
  public void testOf() {
    var checkedException = new Exception();
    assertThat(WrappedExceptions.of(checkedException))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCause(checkedException)
        .hasMessage("Wrapped checked exception");
  }
}
