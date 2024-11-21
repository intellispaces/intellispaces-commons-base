package tech.intellispaces.entity.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AssumptionViolationException}.
 */
public class AssumptionViolationExceptionTest {

  @Test
  public void testInstantiation_whenDefault() {
    AssumptionViolationException exception = new AssumptionViolationException();
    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isNull();
  }

  @Test
  public void testInstantiation_whenMessage() {
    AssumptionViolationException exception = new AssumptionViolationException("Exception message");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }

  @Test
  public void testInstantiation_whenCause() {
    Exception cause = new RuntimeException();
    AssumptionViolationException exception = new AssumptionViolationException(cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo(cause.toString());
  }

  @Test
  public void testInstantiation_whenCauseAndMessage() {
    Exception cause = new RuntimeException();
    AssumptionViolationException exception = new AssumptionViolationException("Exception message", cause);
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
