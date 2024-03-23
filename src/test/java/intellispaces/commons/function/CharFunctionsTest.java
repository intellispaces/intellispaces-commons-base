package intellispaces.commons.function;

import intellispaces.commons.function.CharFunctions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CharFunctions}.
 */
public class CharFunctionsTest {

  @Test
  public void testIsBlankChar() {
    assertThat(CharFunctions.isBlankChar(' ')).isTrue();
    assertThat(CharFunctions.isBlankChar('\t')).isTrue();

    assertThat(CharFunctions.isBlankChar('\n')).isFalse();
    assertThat(CharFunctions.isBlankChar('\r')).isFalse();

    assertThat(CharFunctions.isBlankChar('a')).isFalse();
    assertThat(CharFunctions.isBlankChar('1')).isFalse();
  }
}
