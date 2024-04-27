package tech.intellispacesframework.commons.exception;

import tech.intellispacesframework.commons.function.ThrowingBiFunction;
import tech.intellispacesframework.commons.function.ThrowingFunction;
import tech.intellispacesframework.commons.function.ThrowingQuadFunction;
import tech.intellispacesframework.commons.function.ThrowingTriFunction;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static tech.intellispacesframework.commons.exception.ExceptionFunctions.coverThrowableConsumer;
import static tech.intellispacesframework.commons.exception.ExceptionFunctions.coverThrowableFunction;
import static tech.intellispacesframework.commons.exception.ExceptionFunctions.uncoverThrowable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ExceptionFunctions} class.
 */
public class ExceptionFunctionsTest {

  @Test
  public void testCoverThrowableFunction_whenCheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testCoverThrowableConsumer_whenCheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(ExceptionFunctions.coverThrowableConsumer(this::throwingCheckedConsumer))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testCoverThrowableFunction_whenUncheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingUncheckedFunction))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class);
  }

  @Test
  public void testCoverThrowableConsumer_whenUncheckedException() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(ExceptionFunctions.coverThrowableConsumer(this::throwingUncheckedConsumer))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class);
  }

  @Test
  public void testCoverThrowableFunction_whenCheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(IllegalArgumentException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testCoverThrowableConsumer_whenCheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(ExceptionFunctions.coverThrowableConsumer(this::throwingCheckedConsumer, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(IllegalArgumentException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testCoverThrowableFunction_whenUncheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingUncheckedFunction, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class)
        .hasNoCause();
  }

  @Test
  public void testCoverThrowableConsumer_whenUncheckedExceptionAndExceptionFactory() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> Stream.of("a", "", "b")
        .peek(ExceptionFunctions.coverThrowableConsumer(this::throwingUncheckedConsumer, IllegalArgumentException::new))
        .toList();

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(RuntimeException.class)
        .hasNoCause();
  }

  @Test
  public void testUncoverThrowable_whenRunnableAndExactMatch() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> ExceptionFunctions.uncoverThrowable(PossibleViolationException.class, () -> Stream.of("a", "", "b")
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenRunnableAndBaseExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> ExceptionFunctions.uncoverThrowable(Exception.class, () -> Stream.of("a", "", "b")
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenRunnableAndOtherExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> ExceptionFunctions.uncoverThrowable(IOException.class, () -> Stream.of("a", "", "b")
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenFunctionAndExactMatch() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> ExceptionFunctions.uncoverThrowable(PossibleViolationException.class, stream, (s) -> s
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenFunctionAndBaseExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> ExceptionFunctions.uncoverThrowable(Exception.class, stream, (s) -> s
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncoverThrowable_whenFunctionAndOtherExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> ExceptionFunctions.uncoverThrowable(IOException.class, stream, (s) -> s
        .map(ExceptionFunctions.coverThrowableFunction(this::throwingCheckedFunction))
        .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testCoverException_whenFunction() {
    // Given
    ThrowingFunction<String, Integer, PossibleViolationException> function1 = s -> 1;
    ThrowingFunction<String, Integer, PossibleViolationException> function2 = s -> { throw new PossibleViolationException(); };
    ThrowingFunction<String, Integer, PossibleViolationException> function3 = s -> { throw new UnexpectedViolationException(); };

    // Then
    Assertions.assertThat(ExceptionFunctions.coverException(function1, "")).isEqualTo(1);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function2, ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function3, ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testCoverException_whenBiFunction() {
    // Given
    ThrowingBiFunction<String, String, Integer, PossibleViolationException> function1 = (s1, s2) -> 1;
    ThrowingBiFunction<String, String, Integer, PossibleViolationException> function2 = (s1, s2) -> { throw new PossibleViolationException(); };
    ThrowingBiFunction<String, String, Integer, PossibleViolationException> function3 = (s1, s2) -> { throw new UnexpectedViolationException(); };

    // Then
    Assertions.assertThat(ExceptionFunctions.coverException(function1, "", "")).isEqualTo(1);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function2, "", ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function3, "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testCoverException_whenTriFunction() {
    // Given
    ThrowingTriFunction<String, String, String, Integer, PossibleViolationException> function1 = (s1, s2, s3) -> 1;
    ThrowingTriFunction<String, String, String, Integer, PossibleViolationException> function2 = (s1, s2, s3) -> { throw new PossibleViolationException(); };
    ThrowingTriFunction<String, String, String, Integer, PossibleViolationException> function3 = (s1, s2, s3) -> { throw new UnexpectedViolationException(); };

    // Then
    Assertions.assertThat(ExceptionFunctions.coverException(function1, "", "", "")).isEqualTo(1);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function2, "", "", ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function3, "", "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testCoverException_whenQuadFunction() {
    // Given
    ThrowingQuadFunction<String, String, String, String, Integer, PossibleViolationException> function1 = (s1, s2, s3, s4) -> 1;
    ThrowingQuadFunction<String, String, String, String, Integer, PossibleViolationException> function2 = (s1, s2, s3, s4) -> {
      throw new PossibleViolationException();
    };
    ThrowingQuadFunction<String, String, String, String, Integer, PossibleViolationException> function3 = (s1, s2, s3, s4) -> {
      throw new UnexpectedViolationException();
    };

    // Then
    Assertions.assertThat(ExceptionFunctions.coverException(function1, "", "", "", "")).isEqualTo(1);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function2, "", "", "", ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> ExceptionFunctions.coverException(function3, "", "", "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  private Character throwingCheckedFunction(String string) throws PossibleViolationException {
    if (string.isEmpty()) {
      throw PossibleViolationException.withMessage("Empty string");
    }
    return string.charAt(0);
  }

  private void throwingCheckedConsumer(String string) throws PossibleViolationException {
    if (string.isEmpty()) {
      throw PossibleViolationException.withMessage("Empty string");
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
