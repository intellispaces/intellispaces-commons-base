package tech.intellispaces.basic.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
  }
}
