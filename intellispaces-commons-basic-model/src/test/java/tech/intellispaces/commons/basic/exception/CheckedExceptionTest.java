package tech.intellispaces.commons.basic.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CheckedException}.
 */
public class CheckedExceptionTest {

  @Test
  public void testInstantiation_whenDefault() {
    CheckedException exception = new CheckedException();
    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isNull();
  }

  @Test
  public void testInstantiation_whenMessage() {
    CheckedException exception = new CheckedException("Exception message");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }

  @Test
  public void testInstantiation_whenCause() {
    Exception cause = new RuntimeException();
    CheckedException exception = new CheckedException(cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo(cause.toString());
  }

  @Test
  public void testInstantiation_whenCauseAndMessage() {
    Exception cause = new RuntimeException();
    CheckedException exception = new CheckedException("Exception message", cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
