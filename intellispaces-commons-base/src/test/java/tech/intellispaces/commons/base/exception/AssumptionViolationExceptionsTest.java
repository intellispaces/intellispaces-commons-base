package tech.intellispaces.commons.base.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CheckedExceptions} class.
 */
public class AssumptionViolationExceptionsTest {

  @Test
  public void testWithCause() {
    var cause = new RuntimeException();
    assertThat(CheckedExceptions.withCause(cause))
        .isExactlyInstanceOf(CheckedException.class)
        .hasCause(cause)
        .hasMessage(null);
  }

  @Test
  public void testWithMessage() {
    var message = "The message";
    assertThat(CheckedExceptions.withMessage(message))
        .isExactlyInstanceOf(CheckedException.class)
        .hasCause(null)
        .hasMessage(message);
  }

  @Test
  public void testWithCauseAndMessage() {
    var cause = new RuntimeException();
    var message = "The message";
    assertThat(CheckedExceptions.withCauseAndMessage(cause, message))
        .isExactlyInstanceOf(CheckedException.class)
        .hasCause(cause)
        .hasMessage(message);
  }

  @Test
  public void testWithTemplateMessage() {
    var template = "The message '{0}'";
    assertThat(CheckedExceptions.withMessage(template, "abc"))
        .isExactlyInstanceOf(CheckedException.class)
        .hasCause(null)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithCauseAndTemplateMessage() {
    var cause = new RuntimeException();
    var template = "The message '{0}'";
    assertThat(CheckedExceptions.withCauseAndMessage(cause, template, "abc"))
        .isExactlyInstanceOf(CheckedException.class)
        .hasCause(cause)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithFormattedMessage() {
    var template = "The message ''{0}''";
    assertThat(CheckedExceptions.withFormattedMessage(template, "abc"))
        .isExactlyInstanceOf(CheckedException.class)
        .hasCause(null)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithCauseFormattedMessage() {
    var cause = new RuntimeException();
    var template = "The message ''{0}''";
    assertThat(CheckedExceptions.withCauseAndFormattedMessage(cause, template, "abc"))
        .isExactlyInstanceOf(CheckedException.class)
        .hasCause(cause)
        .hasMessage("The message 'abc'");
  }
}
