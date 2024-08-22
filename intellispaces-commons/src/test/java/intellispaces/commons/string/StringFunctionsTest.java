package intellispaces.commons.string;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link StringFunctions}/
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
  public void testFormat() {
    assertThat(StringFunctions.format("a")).isEqualTo("a");
    assertThat(StringFunctions.format("")).isEqualTo("");
    assertThat(StringFunctions.format(null)).isNull();
    assertThat(StringFunctions.format("{}", "a")).isEqualTo("a");
    assertThat(StringFunctions.format("{}b{}", "a", "c")).isEqualTo("abc");
    assertThat(StringFunctions.format("a", null)).isEqualTo("a");
    assertThat(StringFunctions.format("a", null, null)).isEqualTo("a");
    assertThat(StringFunctions.format("{}")).isEqualTo("null");
    assertThat(StringFunctions.format("{}", null)).isEqualTo("null");
    assertThat(StringFunctions.format("{}", null, null)).isEqualTo("null");
    assertThat(StringFunctions.format("a {} {}", "c")).isEqualTo("a c null");
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
  public void testReplaceLast() {
    assertThat(StringFunctions.replaceLast(null, null, null)).isNull();
    assertThat(StringFunctions.replaceLast("aa2aa", null, null)).isEqualTo("aa2aa");
    assertThat(StringFunctions.replaceLast("aa2aa", "aa", null)).isEqualTo("aa2");
    assertThat(StringFunctions.replaceLast("aa2aa", "aa", "")).isEqualTo("aa2");
    assertThat(StringFunctions.replaceLast("aa2aa", "aa", "bbb")).isEqualTo("aa2bbb");
  }

  @Test
  public void testCreateBlankString() {
    assertThat(StringFunctions.createBlankString(-1)).isEmpty();
    assertThat(StringFunctions.createBlankString(0)).isEmpty();
    assertThat(StringFunctions.createBlankString(1)).isEqualTo(" ");
    assertThat(StringFunctions.createBlankString(2)).isEqualTo("  ");
    assertThat(StringFunctions.createBlankString(3)).isEqualTo("   ");
  }
}
