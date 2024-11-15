package tech.intellispaces.entities.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UnexpectedException}.
 */
public class UnexpectedExceptionTest {

  @Test
  public void testInstantiation() {
    Exception cause = new RuntimeException();
    UnexpectedException exception = new UnexpectedException("Exception message", cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
