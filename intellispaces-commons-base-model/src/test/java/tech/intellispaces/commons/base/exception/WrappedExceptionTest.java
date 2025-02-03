package tech.intellispaces.commons.base.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link WrappedException}.
 */
public class WrappedExceptionTest {

  @Test
  public void testInstantiation() {
    Exception checkedException = new RuntimeException();
    WrappedException exception = new WrappedException(checkedException);
    assertThat(exception).isInstanceOf(RuntimeException.class);
    assertThat(exception.getCause()).isSameAs(checkedException);
    assertThat(exception.getMessage()).isEqualTo("Wrapped checked exception");
  }
}
