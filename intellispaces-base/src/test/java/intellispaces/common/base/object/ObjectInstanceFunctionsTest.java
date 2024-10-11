package intellispaces.common.base.object;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for class {@link ObjectInstanceFunctions}.
 */
public class ObjectInstanceFunctionsTest {

  @Test
  public void testEqualsAnyOf_whenTwoOptions() {
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 1, 3)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 3, 1)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 2, 3)).isFalse();

    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3)).isFalse();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenThreeOptions() {
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 1, 2, 3)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 3, 2, 1)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 2, 3, 4)).isFalse();

    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, 4)).isFalse();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenFourOptions() {
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 1, 2, 3, 4)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 4, 3, 2, 1)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 2, 3, 4, 5)).isFalse();

    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, 4, 5)).isFalse();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, 4, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenFiveOptions() {
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 1, 2, 3, 4, 5)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 5, 4, 3, 2, 1)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6)).isFalse();

    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6)).isFalse();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, 4, 5, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenSixOptions() {
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 1, 2, 3, 4, 5, 6)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 6, 5, 4, 3, 2, 1)).isTrue();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6, 7)).isFalse();

    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6, 7)).isFalse();
    assertThat(ObjectInstanceFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6, null)).isTrue();
  }
}
