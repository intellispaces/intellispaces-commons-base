package tech.intellispaces.entities.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AssumptionViolationExceptions} class.
 */
public class AssumptionViolationExceptionsTest {

  @Test
  public void testWithCause() {
    var cause = new RuntimeException();
    assertThat(AssumptionViolationExceptions.withCause(cause))
        .isExactlyInstanceOf(AssumptionViolationException.class)
        .hasCause(cause)
        .hasMessage(null);
  }

  @Test
  public void testWithMessage() {
    var message = "The message";
    assertThat(AssumptionViolationExceptions.withMessage(message))
        .isExactlyInstanceOf(AssumptionViolationException.class)
        .hasCause(null)
        .hasMessage(message);
  }

  @Test
  public void testWithCauseAndMessage() {
    var cause = new RuntimeException();
    var message = "The message";
    assertThat(AssumptionViolationExceptions.withCauseAndMessage(cause, message))
        .isExactlyInstanceOf(AssumptionViolationException.class)
        .hasCause(cause)
        .hasMessage(message);
  }

  @Test
  public void testWithTemplateMessage() {
    var template = "The message '{0}'";
    assertThat(AssumptionViolationExceptions.withMessage(template, "abc"))
        .isExactlyInstanceOf(AssumptionViolationException.class)
        .hasCause(null)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithCauseAndTemplateMessage() {
    var cause = new RuntimeException();
    var template = "The message '{0}'";
    assertThat(AssumptionViolationExceptions.withCauseAndMessage(cause, template, "abc"))
        .isExactlyInstanceOf(AssumptionViolationException.class)
        .hasCause(cause)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithFormattedMessage() {
    var template = "The message ''{0}''";
    assertThat(AssumptionViolationExceptions.withFormattedMessage(template, "abc"))
        .isExactlyInstanceOf(AssumptionViolationException.class)
        .hasCause(null)
        .hasMessage("The message 'abc'");
  }

  @Test
  public void testWithCauseFormattedMessage() {
    var cause = new RuntimeException();
    var template = "The message ''{0}''";
    assertThat(AssumptionViolationExceptions.withCauseAndFormattedMessage(cause, template, "abc"))
        .isExactlyInstanceOf(AssumptionViolationException.class)
        .hasCause(cause)
        .hasMessage("The message 'abc'");
  }
}
