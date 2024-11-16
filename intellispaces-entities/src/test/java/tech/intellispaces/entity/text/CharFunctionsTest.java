package tech.intellispaces.entity.text;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CharFunctions} class.
 */
public class CharFunctionsTest {

  @Test
  public void testIsGapChar() {
    assertThat(CharFunctions.isGapChar(' ')).isTrue();
    assertThat(CharFunctions.isGapChar('\t')).isTrue();

    assertThat(CharFunctions.isGapChar('\n')).isFalse();
    assertThat(CharFunctions.isGapChar('\r')).isFalse();

    assertThat(CharFunctions.isGapChar('a')).isFalse();
    assertThat(CharFunctions.isGapChar('1')).isFalse();
  }
}
