package tech.intellispaces.commons.base.function;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import tech.intellispaces.commons.base.exception.CheckedException;
import tech.intellispaces.commons.base.exception.WrappedException;
import tech.intellispaces.commons.base.sample.ThrowingFunctions;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link Consumers};
 */
public class ConsumersTest {

  @Test
  public void testWrappedConsumer_whenCheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(Consumers.wrappedConsumer(ThrowingFunctions::throwingCheckedConsumer))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
  }

  @Test
  public void testWrappedConsumer_whenUncheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(Consumers.wrappedConsumer(ThrowingFunctions::throwingUncheckedConsumer))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class);
  }

  @Test
  public void testWrappedConsumer_whenCheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(Consumers.wrappedConsumer(ThrowingFunctions::throwingCheckedConsumer, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(IllegalArgumentException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
  }

  @Test
  public void testWrappedConsumer_whenUncheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(Consumers.wrappedConsumer(ThrowingFunctions::throwingUncheckedConsumer, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class)
        .hasNoCause();
  }

  @Test
  public void testIdle() {
    Consumers.idle().accept("abc");
    Consumers.idle(String.class).accept("abc");
  }
}
