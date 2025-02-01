package tech.intellispaces.commons.basic.exception;

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

  @Test
  public void testWithCodeAndMessage() {
    var code = "abcde";
    assertThat(NotImplementedExceptions.withCodeAndMessage(code, "Message"))
        .isExactlyInstanceOf(NotImplementedException.class)
        .hasCause(null)
        .hasMessage("Not implemented yet (abcde). Message");
  }

  @Test
  public void testWithCodeAndMessageTemplate() {
    var code = "abcde";
    assertThat(NotImplementedExceptions.withCodeAndMessage(code, "Message {0}", 1))
        .isExactlyInstanceOf(NotImplementedException.class)
        .hasCause(null)
        .hasMessage("Not implemented yet (abcde). Message 1");
  }
}
