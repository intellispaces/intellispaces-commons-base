package tech.intellispaces.common.entities.data;

import tech.intellispaces.common.entities.exception.UnexpectedException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link References} and {@link DirectReferenceImpl} classes.
 */
public class ReferencesTest {

  @Test
  public void testGet_whenDirectReference() {
    // Given
    String target = "abcde";

    // When
    Reference<String> reference = References.get(target);

    // Then
    assertThat(reference.get()).isEqualTo(target);

    assertThatThrownBy(reference::asOrdinal)
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("This reference cannot be represented as an ordinal value");

    assertThatThrownBy(reference::asKey)
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("This reference cannot be represented as an string key");
  }
}
