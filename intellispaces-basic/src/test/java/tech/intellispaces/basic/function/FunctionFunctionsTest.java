package tech.intellispaces.basic.function;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import tech.intellispaces.basic.exception.CheckedException;
import tech.intellispaces.basic.exception.UnexpectedException;
import tech.intellispaces.basic.exception.WrappedException;
import tech.intellispaces.basic.sample.ThrowingFunctions;

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
        CheckedException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CheckedException.class);
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
    assertThatThrownBy(callable).isExactlyInstanceOf(CheckedException.class);
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
        .hasCauseExactlyInstanceOf(CheckedException.class);
  }

  @Test
  public void testApplyAndWrap_whenFunction() {
    // Given
    ThrowingFunction<String, Integer, CheckedException> function1 = s -> 1;
    ThrowingFunction<String, Integer, CheckedException> function2 = s -> {
      throw new CheckedException();
    };
    ThrowingFunction<String, Integer, CheckedException> function3 = s -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", function3))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndWrap_whenBiFunction() {
    // Given
    ThrowingBiFunction<String, String, Integer, CheckedException> function1 = (s1, s2) -> 1;
    ThrowingBiFunction<String, String, Integer, CheckedException> function2 = (s1, s2) -> {
      throw new CheckedException();
    };
    ThrowingBiFunction<String, String, Integer, CheckedException> function3 = (s1, s2) -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", "", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", function3))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndWrap_whenTriFunction() {
    // Given
    ThrowingTriFunction<String, String, String, Integer, CheckedException> function1 = (s1, s2, s3) -> 1;
    ThrowingTriFunction<String, String, String, Integer, CheckedException> function2 = (s1, s2, s3) -> {
      throw new CheckedException();
    };
    ThrowingTriFunction<String, String, String, Integer, CheckedException> function3 = (s1, s2, s3) -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", "", "", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", "", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", "", function3))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndWrap_whenQuadriFunction() {
    // Given
    ThrowingQuadriFunction<String, String, String, String, Integer, CheckedException> function1 = (s1, s2, s3, s4) -> 1;
    ThrowingQuadriFunction<String, String, String, String, Integer, CheckedException> function2 = (s1, s2, s3, s4) -> {
      throw new CheckedException();
    };
    ThrowingQuadriFunction<String, String, String, String, Integer, CheckedException> function3 = (s1, s2, s3, s4) -> {
      throw new UnexpectedException("Something went wrong");
    };

    // Then
    assertThat(FunctionFunctions.applyAndWrap("", "", "", "", function1)).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndWrap("", "", "", "", function2))
        .isExactlyInstanceOf(WrappedException.class)
        .hasCauseExactlyInstanceOf(CheckedException.class);
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
        CheckedException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CheckedException.class);
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
    assertThatThrownBy(callable).isExactlyInstanceOf(CheckedException.class);
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
        .hasCauseExactlyInstanceOf(CheckedException.class);
  }
}
