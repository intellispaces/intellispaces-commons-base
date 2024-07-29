package tech.intellispaces.commons.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ExceptionFunctions} class.
 */
public class ExceptionFunctionsTest {

  @Test
  public void testCoverIfChecked() {
    assertThat(ExceptionFunctions.coverIfChecked(new RuntimeException("message")))
        .isExactlyInstanceOf(RuntimeException.class)
        .hasMessage("message")
        .hasNoCause();

    assertThat(ExceptionFunctions.coverIfChecked(new Exception("message")))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .cause()
        .isExactlyInstanceOf(Exception.class)
        .hasMessage("message");
  }
}
