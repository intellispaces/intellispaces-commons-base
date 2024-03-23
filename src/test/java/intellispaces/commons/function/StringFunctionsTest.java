package intellispaces.commons.function;

import intellispaces.commons.function.StringFunctions;
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
  }
}
