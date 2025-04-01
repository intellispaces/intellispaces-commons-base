package tech.intellispaces.commons.function;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import tech.intellispaces.commons.exception.CheckedException;
import tech.intellispaces.commons.exception.UnexpectedException;
import tech.intellispaces.commons.exception.WrappedException;
import tech.intellispaces.commons.sample.ThrowingFunctions;

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
    ThrowingFunction3<String, String, String, Integer, CheckedException> function1 = (s1, s2, s3) -> 1;
    ThrowingFunction3<String, String, String, Integer, CheckedException> function2 = (s1, s2, s3) -> {
      throw new CheckedException();
    };
    ThrowingFunction3<String, String, String, Integer, CheckedException> function3 = (s1, s2, s3) -> {
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
    ThrowingFunction4<String, String, String, String, Integer, CheckedException> function1 = (s1, s2, s3, s4) -> 1;
    ThrowingFunction4<String, String, String, String, Integer, CheckedException> function2 = (s1, s2, s3, s4) -> {
      throw new CheckedException();
    };
    ThrowingFunction4<String, String, String, String, Integer, CheckedException> function3 = (s1, s2, s3, s4) -> {
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
