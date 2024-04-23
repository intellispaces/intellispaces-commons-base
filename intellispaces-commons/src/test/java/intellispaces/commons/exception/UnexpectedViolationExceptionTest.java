package intellispaces.commons.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UnexpectedViolationException}.
 */
public class UnexpectedViolationExceptionTest {

  @Test
  public void testInstantiation() {
    Exception cause = new RuntimeException();
    UnexpectedViolationException exception = new UnexpectedViolationException(cause, "Exception {}", "message");
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
