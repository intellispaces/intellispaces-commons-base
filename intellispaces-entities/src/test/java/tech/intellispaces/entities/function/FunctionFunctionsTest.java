package tech.intellispaces.entities.function;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import tech.intellispaces.entities.exception.AssumptionViolationException;
import tech.intellispaces.entities.exception.UnexpectedException;
import tech.intellispaces.entities.exception.WrappedException;
import tech.intellispaces.entities.sample.ThrowingFunctions;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link FunctionFunctions}.
 */
public class FunctionFunctionsTest {

  @Test
  public void testWrapThrowingFunction_whenFunctionAndExactMatch() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUnwrap(
        stream,
        (s) -> s
          .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction))
          .toList(),
        AssumptionViolationException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(AssumptionViolationException.class);
  }

  @Test
  public void testWrapThrowingFunction_whenFunctionAndBaseExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUnwrap(
        stream,
        (s) -> s
          .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction))
          .toList(),
        Exception.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(AssumptionViolationException.class);
  }

  @Test
  public void testWrapThrowingFunction_whenFunctionAndOtherExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUnwrap(
        stream,
        (s) -> s
          .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction))
          .toList(),
        IOException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
  }

  @Test
  public void testApplyAndWrap_whenFunction() {
    // Given
    ThrowingFunction<String, Integer, AssumptionViolationException> function1 = s -> 1;
    ThrowingFunction<String, Integer, AssumptionViolationException> function2 = s -> {
      throw new AssumptionViolationException();
    };
    ThrowingFunction<String, Integer, AssumptionViolationException> function3 = s -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", function3))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndWrap_whenBiFunction() {
    // Given
    ThrowingBiFunction<String, String, Integer, AssumptionViolationException> function1 = (s1, s2) -> 1;
    ThrowingBiFunction<String, String, Integer, AssumptionViolationException> function2 = (s1, s2) -> {
      throw new AssumptionViolationException();
    };
    ThrowingBiFunction<String, String, Integer, AssumptionViolationException> function3 = (s1, s2) -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", "", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", function3))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndWrap_whenTriFunction() {
    // Given
    ThrowingTriFunction<String, String, String, Integer, AssumptionViolationException> function1 = (s1, s2, s3) -> 1;
    ThrowingTriFunction<String, String, String, Integer, AssumptionViolationException> function2 = (s1, s2, s3) -> {
      throw new AssumptionViolationException();
    };
    ThrowingTriFunction<String, String, String, Integer, AssumptionViolationException> function3 = (s1, s2, s3) -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", "", "", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", "", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", "", function3))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndWrap_whenQuadriFunction() {
    // Given
    ThrowingQuadriFunction<String, String, String, String, Integer, AssumptionViolationException> function1 = (s1, s2, s3, s4) -> 1;
    ThrowingQuadriFunction<String, String, String, String, Integer, AssumptionViolationException> function2 = (s1, s2, s3, s4) -> {
      throw new AssumptionViolationException();
    };
    ThrowingQuadriFunction<String, String, String, String, Integer, AssumptionViolationException> function3 = (s1, s2, s3, s4) -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", "", "", "", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", "", "", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", "", "", function3))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasNoCause();
  }

  @Test
  public void testUnwrap_whenRunnableAndExactMatch() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.runAndUnwrap(
        () -> Stream.of("a", "", "b")
            .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction))
            .toList(),
        AssumptionViolationException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(AssumptionViolationException.class);
  }

  @Test
  public void testUnwrap_whenRunnableAndBaseExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.runAndUnwrap(
        () -> Stream.of("a", "", "b")
            .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction))
            .toList(),
        Exception.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(AssumptionViolationException.class);
  }

  @Test
  public void testUnwrap_whenRunnableAndOtherExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.runAndUnwrap(
        () -> Stream.of("a", "", "b")
            .map(Functions.wrapThrowingFunction(ThrowingFunctions::throwingCheckedFunction))
            .toList(),
        IOException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(AssumptionViolationException.class);
  }
}
