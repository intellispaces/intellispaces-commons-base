package intellispaces.common.base.text;

import intellispaces.common.base.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link StringFunctions}.
 */
public class StringFunctionsTest {

  @Test
  public void testIsNullOrBlank() {
    assertThat(StringFunctions.isNullOrBlank("a")).isFalse();
    assertThat(StringFunctions.isNullOrBlank("")).isTrue();
    assertThat(StringFunctions.isNullOrBlank(" ")).isTrue();
    assertThat(StringFunctions.isNullOrBlank("\t")).isTrue();
    assertThat(StringFunctions.isNullOrBlank("\r\n")).isTrue();
    assertThat(StringFunctions.isNullOrBlank(null)).isTrue();
  }

  @Test
  public void testIsNotBlank() {
    assertThat(StringFunctions.isNotBlank("a")).isTrue();
    assertThat(StringFunctions.isNotBlank("")).isFalse();
    assertThat(StringFunctions.isNotBlank(" ")).isFalse();
    assertThat(StringFunctions.isNotBlank("\t")).isFalse();
    assertThat(StringFunctions.isNotBlank("\r\n")).isFalse();
    assertThat(StringFunctions.isNotBlank(null)).isFalse();
  }

  @Test
  public void testCapitalizeFirstLetter() {
    assertThat(StringFunctions.capitalizeFirstLetter(null)).isNull();
    assertThat(StringFunctions.capitalizeFirstLetter("")).isEqualTo("");
    assertThat(StringFunctions.capitalizeFirstLetter("a")).isEqualTo("A");
    assertThat(StringFunctions.capitalizeFirstLetter("abc")).isEqualTo("Abc");
    assertThat(StringFunctions.capitalizeFirstLetter("Abc")).isEqualTo("Abc");
  }

  @Test
  public void testLowercaseFirstLetter() {
    assertThat(StringFunctions.lowercaseFirstLetter(null)).isNull();
    assertThat(StringFunctions.lowercaseFirstLetter("")).isEqualTo("");
    assertThat(StringFunctions.lowercaseFirstLetter("A")).isEqualTo("a");
    assertThat(StringFunctions.lowercaseFirstLetter("ABC")).isEqualTo("aBC");
    assertThat(StringFunctions.lowercaseFirstLetter("aBC")).isEqualTo("aBC");
  }

  @Test
  public void testNumberSubstrings() {
    assertThat(StringFunctions.numberSubstrings(null, null)).isEqualTo(0);
    assertThat(StringFunctions.numberSubstrings("abc", "a")).isEqualTo(1);
    assertThat(StringFunctions.numberSubstrings("abc", "b")).isEqualTo(1);
    assertThat(StringFunctions.numberSubstrings("abc", "c")).isEqualTo(1);
    assertThat(StringFunctions.numberSubstrings("abc", "d")).isEqualTo(0);
    assertThat(StringFunctions.numberSubstrings("abcbd", "b")).isEqualTo(2);
  }

  @Test
  public void testReplaceLast() {
    assertThat(StringFunctions.replaceLast(null, null, null)).isNull();
    assertThat(StringFunctions.replaceLast("aa2aa", null, null)).isEqualTo("aa2aa");
    assertThat(StringFunctions.replaceLast("aa2aa", "aa", null)).isEqualTo("aa2");
    assertThat(StringFunctions.replaceLast("aa2aa", "aa", "")).isEqualTo("aa2");
    assertThat(StringFunctions.replaceLast("aa2aa", "aa", "bbb")).isEqualTo("aa2bbb");
    assertThat(StringFunctions.replaceLast("aa2aa", "b", "bb")).isEqualTo("aa2aa");
  }

  @Test
  public void testReplaceTailOrElseThrow() {
    assertThat(StringFunctions.replaceTailOrElseThrow("abc", "c", "d")).isEqualTo("abd");
    assertThat(StringFunctions.replaceTailOrElseThrow("abc", "bc", "def")).isEqualTo("adef");
    assertThat(StringFunctions.replaceTailOrElseThrow("abc", "bc", "")).isEqualTo("a");
    assertThat(StringFunctions.replaceTailOrElseThrow("abc", "bc", null)).isEqualTo("a");

    assertThatThrownBy(() -> StringFunctions.replaceTailOrElseThrow(null, "a", "b"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string is null");
    assertThatThrownBy(() -> StringFunctions.replaceTailOrElseThrow("abc", null, "b"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Substring is null");
    assertThatThrownBy(() -> StringFunctions.replaceTailOrElseThrow("abc", "d", "e"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string 'abc' does not contain tail 'd'");
    assertThatThrownBy(() -> StringFunctions.replaceTailOrElseThrow("abc", "b", "d"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string 'abc' does not contain tail 'b'");
  }

  @Test
  public void testReplaceSingleOrElseThrow() {
    assertThat(StringFunctions.replaceSingleOrElseThrow("abc", "c", "d")).isEqualTo("abd");
    assertThat(StringFunctions.replaceSingleOrElseThrow("abce", "c", "d")).isEqualTo("abde");
    assertThat(StringFunctions.replaceSingleOrElseThrow("abce", "c", "")).isEqualTo("abe");
    assertThat(StringFunctions.replaceSingleOrElseThrow("abce", "c", null)).isEqualTo("abe");
    assertThat(StringFunctions.replaceSingleOrElseThrow("abc", "bc", "def")).isEqualTo("adef");

    assertThatThrownBy(() -> StringFunctions.replaceSingleOrElseThrow(null, "a", "b"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string is null");
    assertThatThrownBy(() -> StringFunctions.replaceSingleOrElseThrow("abc", null, "b"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Substring is null");
    assertThatThrownBy(() -> StringFunctions.replaceSingleOrElseThrow("abc", "d", "e"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string 'abc' does not contain substring 'd'");
    assertThatThrownBy(() -> StringFunctions.replaceSingleOrElseThrow("abcb", "b", "d"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string 'abcb' contains more than one substrings 'b'");
  }

  @Test
  public void testRemoveTailOrElseThrow() {
    assertThat(StringFunctions.removeTailOrElseThrow("abc", "c")).isEqualTo("ab");
    assertThat(StringFunctions.removeTailOrElseThrow("abc", "bc")).isEqualTo("a");
    assertThat(StringFunctions.removeTailOrElseThrow("abc", "abc")).isEqualTo("");

    assertThatThrownBy(() -> StringFunctions.removeTailOrElseThrow(null, "a"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string is null");
    assertThatThrownBy(() -> StringFunctions.removeTailOrElseThrow("abc", null))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Substring is null");
    assertThatThrownBy(() -> StringFunctions.removeTailOrElseThrow("abc", "d"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Source string 'abc' does not contain tail 'd'");
  }

  @Test
  public void testCreateBlankString() {
    assertThat(StringFunctions.createBlankString(-1)).isEmpty();
    assertThat(StringFunctions.createBlankString(0)).isEmpty();
    assertThat(StringFunctions.createBlankString(1)).isEqualTo(" ");
    assertThat(StringFunctions.createBlankString(2)).isEqualTo("  ");
    assertThat(StringFunctions.createBlankString(3)).isEqualTo("   ");
  }

  @Test
  public void testStringToInputStream() {
    assertThat(StringFunctions.stringToInputStream(null)).isNull();
    assertThat(StringFunctions.stringToInputStream("")).isEmpty();
    assertThat(StringFunctions.stringToInputStream("abc")).hasContent("abc");
  }
}
