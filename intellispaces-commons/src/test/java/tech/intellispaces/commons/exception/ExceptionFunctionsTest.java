package tech.intellispaces.commons.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ExceptionFunctions} class.
 */
public class ExceptionFunctionsTest {

  @Test
  public void testWrapIfChecked() {
    assertThat(ExceptionFunctions.wrapIfChecked(new RuntimeException("message")))
        .isExactlyInstanceOf(RuntimeException.class)
        .hasMessage("message")
        .hasNoCause();

    assertThat(ExceptionFunctions.wrapIfChecked(new Exception("message")))
        .isExactlyInstanceOf(WrappedException.class)
        .cause()
        .isExactlyInstanceOf(Exception.class)
        .hasMessage("message");
  }
}
