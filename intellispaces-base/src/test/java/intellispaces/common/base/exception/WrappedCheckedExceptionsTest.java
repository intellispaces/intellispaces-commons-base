package intellispaces.common.base.exception;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link WrappedCheckedExceptions} class.
 */
public class WrappedCheckedExceptionsTest {

  @Test
  public void testWithCause() {
    var cause = new RuntimeException();
    assertThat(WrappedCheckedExceptions.withCause(cause))
        .isExactlyInstanceOf(WrappedCheckedException.class)
        .hasCause(cause)
        .hasMessage(null);
  }
}
