package tech.intellispaces.basic.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link UnexpectedException}.
 */
public class UnexpectedExceptionTest {

  @Test
  public void testInstantiation_whenMessage() {
    UnexpectedException exception = new UnexpectedException("Exception message");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }

  @Test
  public void testInstantiation_whenCause() {
    Exception cause = new RuntimeException();
    UnexpectedException exception = new UnexpectedException(cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo(cause.toString());
  }

  @Test
  public void testInstantiation_whenCauseAndMessage() {
    Exception cause = new RuntimeException();
    UnexpectedException exception = new UnexpectedException("Exception message", cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
