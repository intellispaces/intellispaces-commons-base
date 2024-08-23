package intellispaces.commons.function;

import intellispaces.commons.TestFunctionSamples;
import intellispaces.commons.exception.CoveredCheckedException;
import intellispaces.commons.exception.PossibleViolationException;
import intellispaces.commons.exception.UnexpectedViolationException;
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
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUncoverIfCovered(
        (s) -> s
          .map(Functions.coveredThrowableFunction(TestFunctionSamples::throwingCheckedFunction))
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
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUncoverIfCovered(
        (s) -> s
          .map(Functions.coveredThrowableFunction(TestFunctionSamples::throwingCheckedFunction))
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
    ThrowableAssert.ThrowingCallable callable = () -> FunctionFunctions.applyAndUncoverIfCovered(
        (s) -> s
          .map(Functions.coveredThrowableFunction(TestFunctionSamples::throwingCheckedFunction))
          .toList(),
        stream,
        IOException.class);

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testApplyAndCoverIfChecked_whenFunction() {
    // Given
    ThrowableFunction<String, Integer, PossibleViolationException> function1 = s -> 1;
    ThrowableFunction<String, Integer, PossibleViolationException> function2 = s -> {
      throw new PossibleViolationException();
    };
    ThrowableFunction<String, Integer, PossibleViolationException> function3 = s -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCoverIfChecked(function1, "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function2, ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function3, ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndCoverIfChecked_whenBiFunction() {
    // Given
    ThrowableBiFunction<String, String, Integer, PossibleViolationException> function1 = (s1, s2) -> 1;
    ThrowableBiFunction<String, String, Integer, PossibleViolationException> function2 = (s1, s2) -> {
      throw new PossibleViolationException();
    };
    ThrowableBiFunction<String, String, Integer, PossibleViolationException> function3 = (s1, s2) -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCoverIfChecked(function1, "", "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function2, "", ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function3, "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndCoverIfChecked_whenTriFunction() {
    // Given
    ThrowableTriFunction<String, String, String, Integer, PossibleViolationException> function1 = (s1, s2, s3) -> 1;
    ThrowableTriFunction<String, String, String, Integer, PossibleViolationException> function2 = (s1, s2, s3) -> {
      throw new PossibleViolationException();
    };
    ThrowableTriFunction<String, String, String, Integer, PossibleViolationException> function3 = (s1, s2, s3) -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCoverIfChecked(function1, "", "", "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function2, "", "", ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function3, "", "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }

  @Test
  public void testApplyAndCoverIfChecked_whenQuadFunction() {
    // Given
    ThrowableQuadFunction<String, String, String, String, Integer, PossibleViolationException> function1 = (s1, s2, s3, s4) -> 1;
    ThrowableQuadFunction<String, String, String, String, Integer, PossibleViolationException> function2 = (s1, s2, s3, s4) -> {
      throw new PossibleViolationException();
    };
    ThrowableQuadFunction<String, String, String, String, Integer, PossibleViolationException> function3 = (s1, s2, s3, s4) -> {
      throw new UnexpectedViolationException();
    };

    // Then
    assertThat(FunctionFunctions.applyAndCoverIfChecked(function1, "", "", "", "")).isEqualTo(1);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function2, "", "", "", ""))
        .isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
    assertThatThrownBy(() -> FunctionFunctions.applyAndCoverIfChecked(function3, "", "", "", ""))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasNoCause();
  }
}
