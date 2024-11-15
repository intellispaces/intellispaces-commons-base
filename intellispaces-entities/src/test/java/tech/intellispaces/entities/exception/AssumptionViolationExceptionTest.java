package tech.intellispaces.entities.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AssumptionViolationException}.
 */
public class AssumptionViolationExceptionTest {

  @Test
  public void testInstantiation() {
    Exception cause = new RuntimeException();
    AssumptionViolationException exception = new AssumptionViolationException("Exception message", cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
