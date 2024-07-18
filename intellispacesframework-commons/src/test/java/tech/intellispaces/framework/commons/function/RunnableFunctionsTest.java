package tech.intellispaces.framework.commons.function;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import tech.intellispaces.framework.commons.TestFunctionSamples;
import tech.intellispaces.framework.commons.exception.CoveredCheckedException;
import tech.intellispaces.framework.commons.exception.PossibleViolationException;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link RunnableFunctions}
 */
public class RunnableFunctionsTest {

  @Test
  public void testUncover_whenRunnableAndExactMatch() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> RunnableFunctions.runAndUncoverIfCovered(
        PossibleViolationException.class,
        () -> Stream.of("a", "", "b")
          .map(Functions.coveredThrowableFunction(TestFunctionSamples::throwingCheckedFunction))
          .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncover_whenRunnableAndBaseExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> RunnableFunctions.runAndUncoverIfCovered(
        Exception.class,
        () -> Stream.of("a", "", "b")
          .map(Functions.coveredThrowableFunction(TestFunctionSamples::throwingCheckedFunction))
          .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(PossibleViolationException.class);
  }

  @Test
  public void testUncover_whenRunnableAndOtherExceptionSpecified() {
    // When
    ThrowableAssert.ThrowingCallable callable = () -> RunnableFunctions.runAndUncoverIfCovered(
        IOException.class,
        () -> Stream.of("a", "", "b")
          .map(Functions.coveredThrowableFunction(TestFunctionSamples::throwingCheckedFunction))
          .toList());

    // Then
    assertThatThrownBy(callable).isExactlyInstanceOf(CoveredCheckedException.class)
        .hasCauseExactlyInstanceOf(PossibleViolationException.class);
  }
}
