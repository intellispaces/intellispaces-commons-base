package intellispaces.common.base.function;

import intellispaces.common.base.exception.AssumptionViolationException;
import intellispaces.common.base.exception.WrappedException;
import intellispaces.common.base.sample.ThrowingFunctions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

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
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
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
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
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
