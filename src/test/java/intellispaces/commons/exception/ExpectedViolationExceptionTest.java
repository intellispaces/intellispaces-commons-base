package intellispaces.commons.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ExpectedViolationException}.
 */
public class ExpectedViolationExceptionTest {

  @Test
  public void testInstantiation() {
    Exception cause = new RuntimeException();
    ExpectedViolationException exception = new ExpectedViolationException(cause, "Exception {}", "message");
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
