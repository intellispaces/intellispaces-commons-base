package tech.intellispaces.basic.data;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Triads} and {@link TriadImpl} classes.
 */
public class TriadsTest {

  @Test
  public void testGet() {
    // Given
    var value1 = "abc";
    var value2 = "dbe";
    var value3 = "fgh";

    // When
    Triad<String, String, String> triad = Triads.get(value1, value2, value3);

    // Then
    assertThat(triad.value1()).isEqualTo(value1);
    assertThat(triad.value2()).isEqualTo(value2);
    assertThat(triad.value3()).isEqualTo(value3);
  }

  @Test
  public void testEquals() {
    var value1 = "abc";
    var value2 = "dbe";
    var value3 = "fgh";

    Triad<String, String, String> triad = Triads.get(value1, value2, value3);
    assertThat(triad).isEqualTo(triad);
    assertThat(triad).isNotEqualTo(null);
    assertThat(triad).isNotEqualTo("String");

    assertThat(Triads.get(value1, value2, value3)).isEqualTo(Triads.get(value1, value2, value3));
  }

  @Test
  public void testHashCode() {
    var value1 = "abc";
    var value2 = "dbe";
    var value3 = "fgh";
    Triad<String, String, String> triad = Triads.get(value1, value2, value3);

    assertThat(triad.hashCode()).isEqualTo(Objects.hash(value1, value2, value3));
  }
}
