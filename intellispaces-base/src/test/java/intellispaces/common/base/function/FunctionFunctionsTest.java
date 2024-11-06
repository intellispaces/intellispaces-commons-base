package intellispaces.common.base.function;

import intellispaces.common.base.exception.CoveredException;
import intellispaces.common.base.exception.PossibleViolationException;
import intellispaces.common.base.exception.UnexpectedViolationException;
import intellispaces.common.base.sample.ThrowingFunctions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link FunctionFunctions}.
 */
public class FunctionFunctionsTest {

  @Test
  public void testApplyAndUncoverIfCovered_whenFunctionAndExactMatch() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUncover(
        (s) -> s
          .map(Functions.coveredFunction(ThrowingFunctions::throwingCheckedFunction))
          .toList(),
        stream,
        PossibleViolationException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testApplyAndUncoverIfCovered_whenFunctionAndBaseExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUncover(
        (s) -> s
          .map(Functions.coveredFunction(ThrowingFunctions::throwingCheckedFunction))
          .toList(),
        stream,
        Exception.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testApplyAndUncoverIfCovered_whenFunctionAndOtherExceptionSpecified() {
    // Given
    Stream<String> stream = Stream.of("a", "", "b");

    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUncover(
        (s) -> s
          .map(Functions.coveredFunction(ThrowingFunctions::throwingCheckedFunction))
          .toList(),
        stream,
        IOException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testApplyAndCover_whenFunction() {
    // Given
    ThrowingFunction<String, Integer, PossibleViolationException> function1 = s -> 1;
    ThrowingFunction<String, Integer, PossibleViolationException> function2 = s -> {
      throw new PossibleViolationException();
    };
    ThrowingFunction<String, Integer, PossibleViolationException> function3 = s -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCover(function1, "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function2, ""))
        .isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function3, ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndCover_whenBiFunction() {
    // Given
    ThrowingBiFunction<String, String, Integer, PossibleViolationException> function1 = (s1, s2) -> 1;
    ThrowingBiFunction<String, String, Integer, PossibleViolationException> function2 = (s1, s2) -> {
      throw new PossibleViolationException();
    };
    ThrowingBiFunction<String, String, Integer, PossibleViolationException> function3 = (s1, s2) -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCover(function1, "", "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function2, "", ""))
        .isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function3, "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndCover_whenTriFunction() {
    // Given
    ThrowingTriFunction<String, String, String, Integer, PossibleViolationException> function1 = (s1, s2, s3) -> 1;
    ThrowingTriFunction<String, String, String, Integer, PossibleViolationException> function2 = (s1, s2, s3) -> {
      throw new PossibleViolationException();
    };
    ThrowingTriFunction<String, String, String, Integer, PossibleViolationException> function3 = (s1, s2, s3) -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCover(function1, "", "", "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function2, "", "", ""))
        .isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function3, "", "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndCover_whenQuadFunction() {
    // Given
    ThrowingQuadFunction<String, String, String, String, Integer, PossibleViolationException> function1 = (s1, s2, s3, s4) -> 1;
    ThrowingQuadFunction<String, String, String, String, Integer, PossibleViolationException> function2 = (s1, s2, s3, s4) -> {
      throw new PossibleViolationException();
    };
    ThrowingQuadFunction<String, String, String, String, Integer, PossibleViolationException> function3 = (s1, s2, s3, s4) -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCover(function1, "", "", "", "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function2, "", "", "", ""))
        .isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCover(function3, "", "", "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testUncover_whenRunnableAndExactMatch() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.runAndUncover(
        () -> Stream.of("a", "", "b")
            .map(Functions.coveredFunction(ThrowingFunctions::throwingCheckedFunction))
            .toList(),
        PossibleViolationException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncover_whenRunnableAndBaseExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.runAndUncover(
        () -> Stream.of("a", "", "b")
            .map(Functions.coveredFunction(ThrowingFunctions::throwingCheckedFunction))
            .toList(),
        Exception.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncover_whenRunnableAndOtherExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.runAndUncover(
        () -> Stream.of("a", "", "b")
            .map(Functions.coveredFunction(ThrowingFunctions::throwingCheckedFunction))
            .toList(),
        IOException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }
}
