package tech.intellispaces.commons.basic.function;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import tech.intellispaces.commons.basic.exception.CheckedException;
import tech.intellispaces.commons.basic.exception.WrappedException;
import tech.intellispaces.commons.basic.sample.ThrowingFunctions;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link Functions}.
 */
public class FunctionsTests {

  @Test
  public void testWrapThrowingFunction_whenCheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
  }

  @Test
  public void testWrapThrowingFunction_whenUncheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingUncheckedFunction))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class);
  }

  @Test
  public void testWrapThrowingFunction_whenCheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(IllegalArgumentException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
  }

  @Test
  public void testWrapThrowingFunctionn_whenUncheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingUncheckedFunction, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class)
        .hasNoCause();
  }

}
