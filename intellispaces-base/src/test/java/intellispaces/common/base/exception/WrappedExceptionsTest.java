package intellispaces.common.base.exception;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link WrappedExceptions} class.
 */
public class WrappedExceptionsTest {

  @Test
  public void testOfChecked() {
    var cause = new RuntimeException();
    assertThat(WrappedExceptions.ofChecked(cause))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCause(cause)
        .hasMessage(null);
  }
}
