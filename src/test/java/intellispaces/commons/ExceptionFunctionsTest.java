package intellispaces.commons;

import intellispaces.commons.exception.CoveredException;
import intellispaces.commons.exception.ExpectedViolationException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static intellispaces.commons.ExceptionFunctions.coverThrowableConsumer;
import static intellispaces.commons.ExceptionFunctions.coverThrowableFunction;
import static intellispaces.commons.ExceptionFunctions.uncoverThrowable;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ExceptionFunctions} class.
 */
public class ExceptionFunctionsTest {

  @Test
  public void testCoverThrowableFunction_whenCheckedException() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .map(coverThrowableFunction(this::throwingCheckedFunction))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testCoverThrowableConsumer_whenCheckedException() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .peek(coverThrowableConsumer(this::throwingCheckedConsumer))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testCoverThrowableFunction_whenUncheckedException() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .map(coverThrowableFunction(this::throwingUncheckedFunction))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(RuntimeException.class);
  }

  @Test
  public void testCoverThrowableConsumer_whenUncheckedException() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .peek(coverThrowableConsumer(this::throwingUncheckedConsumer))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(RuntimeException.class);
  }

  @Test
  public void testCoverThrowableFunction_whenCheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .map(coverThrowableFunction(this::throwingCheckedFunction, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(IllegalArgumentException.class)
        .hasCauseExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testCoverThrowableConsumer_whenCheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .peek(coverThrowableConsumer(this::throwingCheckedConsumer, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(IllegalArgumentException.class)
        .hasCauseExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testCoverThrowableFunction_whenUncheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .map(coverThrowableFunction(this::throwingUncheckedFunction, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(RuntimeException.class)
        .hasNoCause();
  }

  @Test
  public void testCoverThrowableConsumer_whenUncheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> Stream.of("a", "", "b")
        .peek(coverThrowableConsumer(this::throwingUncheckedConsumer, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(RuntimeException.class)
        .hasNoCause();
  }

  @Test
  public void testUncoverThrowable_whenRunnableAndExactMatch() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> uncoverThrowable(ExpectedViolationException.class, () -> Stream.of("a", "", "b")
        .map(coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenRunnableAndBaseExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> uncoverThrowable(Exception.class, () -> Stream.of("a", "", "b")
        .map(coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenRunnableAndOtherExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable action = () -> uncoverThrowable(IOException.class, () -> Stream.of("a", "", "b")
        .map(coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenFunctionAndExactMatch() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable action = () -> uncoverThrowable(ExpectedViolationException.class, stream, (s) -> s
        .map(coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenFunctionAndBaseExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable action = () -> uncoverThrowable(Exception.class, stream, (s) -> s
        .map(coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(ExpectedViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenFunctionAndOtherExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable action = () -> uncoverThrowable(IOException.class, stream, (s) -> s
        .map(coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(ExpectedViolationException.class);
  }

  private Character throwingCheckedFunction(String string) throws ExpectedViolationException {
    if (string.isEmpty()) {
      throw new ExpectedViolationException("Empty string");
    }
    return string.charAt(0);
  }

  private void throwingCheckedConsumer(String string) throws ExpectedViolationException {
    if (string.isEmpty()) {
      throw new ExpectedViolationException("Empty string");
    }
  }

  private Character throwingUncheckedFunction(String string) {
    if (string.isEmpty()) {
      throw new RuntimeException("Empty string");
    }
    return string.charAt(0);
  }

  private void throwingUncheckedConsumer(String string) {
    if (string.isEmpty()) {
      throw new RuntimeException("Empty string");
    }
  }
}
