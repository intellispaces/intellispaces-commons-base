package intellispaces.common.utils.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link PossibleViolationException}.
 */
public class PossibleViolationExceptionTest {

  @Test
  public void testInstantiation() {
    Exception cause = new RuntimeException();
    PossibleViolationException exception = new PossibleViolationException(cause, "Exception {0}", "message");
    assertThat(exception.getCause()).isSameAs(cause);
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
