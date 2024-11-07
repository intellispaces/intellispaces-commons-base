package intellispaces.common.base.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link NotImplementedExceptions} class.
 */
public class NotImplementedExceptionsTest {

  @Test
  public void testWithCode() {
    var code = "abcde";
    assertThat(NotImplementedExceptions.withCode(code))
        .isExactlyInstanceOf(NotImplementedException.class)
        .hasCause(null)
        .hasMessage("Not implemented yet (abcde)");
    }
}
