package tech.intellispaces.commons.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link NotImplementedException}.
 */
public class NotImplementedExceptionTest {

  @Test
  public void testInstantiation_whenMessage() {
    NotImplementedException exception = new NotImplementedException("Exception message");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.getMessage()).isEqualTo("Exception message");
  }
}
