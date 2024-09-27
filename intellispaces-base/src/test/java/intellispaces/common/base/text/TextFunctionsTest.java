package intellispaces.common.base.text;

import intellispaces.common.base.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link TextFunctions}/
 */
public class TextFunctionsTest {

  @Test
  public void testIsGapChar() {
    assertThat(TextFunctions.isGapChar(' ')).isTrue();
    assertThat(TextFunctions.isGapChar('\t')).isTrue();

    assertThat(TextFunctions.isGapChar('\n')).isFalse();
    assertThat(TextFunctions.isGapChar('\r')).isFalse();

    assertThat(TextFunctions.isGapChar('a')).isFalse();
    assertThat(TextFunctions.isGapChar('1')).isFalse();
  }

  @Test
  public void testIsNullOrBlank() {
    assertThat(TextFunctions.isNullOrBlank("a")).isFalse();
    assertThat(TextFunctions.isNullOrBlank("")).isTrue();
    assertThat(TextFunctions.isNullOrBlank(" ")).isTrue();
    assertThat(TextFunctions.isNullOrBlank("\t")).isTrue();
    assertThat(TextFunctions.isNullOrBlank("\r\n")).isTrue();
    assertThat(TextFunctions.isNullOrBlank(null)).isTrue();
  }

  @Test
  public void testIsNotBlank() {
    assertThat(TextFunctions.isNotBlank("a")).isTrue();
    assertThat(TextFunctions.isNotBlank("")).isFalse();
    assertThat(TextFunctions.isNotBlank(" ")).isFalse();
    assertThat(TextFunctions.isNotBlank("\t")).isFalse();
    assertThat(TextFunctions.isNotBlank("\r\n")).isFalse();
    assertThat(TextFunctions.isNotBlank(null)).isFalse();
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertThat(TextFunctions.capitalizeFirstLetter(null)).isNull();
    assertThat(TextFunctions.capitalizeFirstLetter("")).isEqualTo("");
    assertThat(TextFunctions.capitalizeFirstLetter("a")).isEqualTo("A");
    assertThat(TextFunctions.capitalizeFirstLetter("abc")).isEqualTo("Abc");
    assertThat(TextFunctions.capitalizeFirstLetter("Abc")).isEqualTo("Abc");
  }

  @Test
  public void testLowercaseFirstLetter() {
    assertThat(TextFunctions.lowercaseFirstLetter(null)).isNull();
    assertThat(TextFunctions.lowercaseFirstLetter("")).isEqualTo("");
    assertThat(TextFunctions.lowercaseFirstLetter("A")).isEqualTo("a");
    assertThat(TextFunctions.lowercaseFirstLetter("ABC")).isEqualTo("aBC");
    assertThat(TextFunctions.lowercaseFirstLetter("aBC")).isEqualTo("aBC");
  }

  @Test
  public void testReplaceLast() {
    assertThat(TextFunctions.replaceLast(null, null, null)).isNull();
    assertThat(TextFunctions.replaceLast("aa2aa", null, null)).isEqualTo("aa2aa");
    assertThat(TextFunctions.replaceLast("aa2aa", "aa", null)).isEqualTo("aa2");
    assertThat(TextFunctions.replaceLast("aa2aa", "aa", "")).isEqualTo("aa2");
    assertThat(TextFunctions.replaceLast("aa2aa", "aa", "bbb")).isEqualTo("aa2bbb");
  }

  @Test
  public void testReplaceEndingOrElseThrow() {
    assertThat(TextFunctions.replaceEndingOrElseThrow("abc", "c", "d")).isEqualTo("abd");
    assertThat(TextFunctions.replaceEndingOrElseThrow("abc", "bc", "def")).isEqualTo("adef");

    assertThatThrownBy(() -> TextFunctions.replaceEndingOrElseThrow(null, "a", "b"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string is null");
    assertThatThrownBy(() -> TextFunctions.replaceEndingOrElseThrow("abc", null, "b"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Ending string is null");
    assertThatThrownBy(() -> TextFunctions.replaceEndingOrElseThrow("abc", "d", "e"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string does not contain ending string");
    assertThatThrownBy(() -> TextFunctions.replaceEndingOrElseThrow("abc", "b", "d"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string does not contain ending string");
  }

  @Test
  public void testCreateBlankString() {
    assertThat(TextFunctions.createBlankString(-1)).isEmpty();
    assertThat(TextFunctions.createBlankString(0)).isEmpty();
    assertThat(TextFunctions.createBlankString(1)).isEqualTo(" ");
    assertThat(TextFunctions.createBlankString(2)).isEqualTo("  ");
    assertThat(TextFunctions.createBlankString(3)).isEqualTo("   ");
  }

  @Test
  public void testStringToInputStream() {
    assertThat(TextFunctions.stringToInputStream(null)).isNull();
    assertThat(TextFunctions.stringToInputStream("")).isEmpty();
    assertThat(TextFunctions.stringToInputStream("abc")).hasContent("abc");
  }
}
