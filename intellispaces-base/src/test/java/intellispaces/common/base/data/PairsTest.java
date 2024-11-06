package intellispaces.common.base.data;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Pairs} and {@link PairImpl} classes.
 */
public class PairsTest {

  @Test
  public void testGet() {
    // Given
    String value1 = "abc";
    String value2 = "dbe";

    // When
    Pair<String, String> pair = Pairs.get(value1, value2);

    // Then
    assertThat(pair.value1()).isEqualTo(value1);
    assertThat(pair.value2()).isEqualTo(value2);
  }

  @Test
  public void testEquals() {
    var value1 = "abc";
    var value2 = "dbe";

    Pair<String, String> pair = Pairs.get(value1, value2);
    assertThat(pair).isEqualTo(pair);
    assertThat(pair).isNotEqualTo(null);
    assertThat(pair).isNotEqualTo("String");

    assertThat(Pairs.get(value1, value2)).isEqualTo(Pairs.get(value1, value2));
  }

  @Test
  public void testHashCode() {
    var value1 = "abc";
    var value2 = "dbe";
    Pair<String, String> pair = Pairs.get(value1, value2);

    assertThat(pair.hashCode()).isEqualTo(Objects.hash(value1, value2));
  }
}
