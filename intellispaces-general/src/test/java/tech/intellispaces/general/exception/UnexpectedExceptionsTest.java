package tech.intellispaces.general.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UnexpectedExceptions} class.
 */
public class UnexpectedExceptionsTest {

  @Test
  public void testWithCause() {
    var cause = new RuntimeException();
    assertThat(UnexpectedExceptions.withCause(cause))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasCause(cause)
        .hasMessage(null);
  }

  @Test
  public void testWithMessage() {
    var message = "The message";
    assertThat(UnexpectedExceptions.withMessage(message))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasCause(null)
        .hasMessage(message);
  }

  @Test
  public void testWithCauseAndMessage() {
    var cause = new RuntimeException();
    var message = "The message";
    assertThat(UnexpectedExceptions.withCauseAndMessage(cause, message))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasCause(cause)
        .hasMessage(message);
  }

  @Test
  public void testWithTemplateMessage() {
    var template = "The message '{0}'";
    assertThat(UnexpectedExceptions.withMessage(template, "abc"))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasCause(null)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithCauseAndTemplateMessage() {
    var cause = new RuntimeException();
    var template = "The message '{0}'";
    assertThat(UnexpectedExceptions.withCauseAndMessage(cause, template, "abc"))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasCause(cause)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithFormattedMessage() {
    var template = "The message ''{0}''";
    assertThat(UnexpectedExceptions.withFormattedMessage(template, "abc"))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasCause(null)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithCauseFormattedMessage() {
    var cause = new RuntimeException();
    var template = "The message ''{0}''";
    assertThat(UnexpectedExceptions.withCauseAndFormattedMessage(cause, template, "abc"))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasCause(cause)
        .hasMessage("The message 'abc'");
  }
}
