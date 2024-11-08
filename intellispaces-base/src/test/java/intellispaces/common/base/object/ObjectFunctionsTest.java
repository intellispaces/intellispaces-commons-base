package intellispaces.common.base.object;

import intellispaces.common.base.exception.UnexpectedException;
import intellispaces.common.base.sample.ClassWithDefaultConstructor;
import intellispaces.common.base.sample.ClassWithDefaultConstructorThatThrowException;
import intellispaces.common.base.sample.ClassWithoutDefaultConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link ObjectFunctions}.
 */
public class ObjectFunctionsTest {

  @Test
  public void testNewInstance_whenHasDefaultConstructor() {
    ClassWithDefaultConstructor instance = ObjectFunctions.newInstance(ClassWithDefaultConstructor.class);
    assertThat(instance).isNotNull();
  }

  @Test
  public void testNewInstance_whenHasNotDefaultConstructor() {
    assertThatThrownBy(() -> ObjectFunctions.newInstance(ClassWithoutDefaultConstructor.class))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Class %s does not contain default constructor without parameters",
            ClassWithoutDefaultConstructor.class.getCanonicalName());
  }

  @Test
  public void testNewInstance_whenHasDefaultConstructorThatThrowException() {
    assertThatThrownBy(() -> ObjectFunctions.newInstance(ClassWithDefaultConstructorThatThrowException.class))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Failed to create instance of the class %s",
            ClassWithDefaultConstructorThatThrowException.class.getCanonicalName());
  }

  @Test
  public void testConvertToInt() {
    assertThat(ObjectFunctions.convertToInt('a')).isEqualTo(97);
    assertThat(ObjectFunctions.convertToInt((byte) 13)).isEqualTo(13);
    assertThat(ObjectFunctions.convertToInt((short) 13)).isEqualTo(13);
    assertThat(ObjectFunctions.convertToInt((13))).isEqualTo(13);

    assertThatThrownBy(() -> ObjectFunctions.convertToInt(13L))
        .isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ObjectFunctions.convertToInt(3.14F))
        .isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ObjectFunctions.convertToInt(3.14D))
        .isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ObjectFunctions.convertToInt("String"))
        .isExactlyInstanceOf(UnexpectedException.class);
  }

  @Test
  public void testConvertToDouble() {
    assertThat(ObjectFunctions.convertToDouble('a')).isEqualTo(97.0);
    assertThat(ObjectFunctions.convertToDouble((byte) 13)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble((short) 13)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble((13))).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble(13L)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble(13F)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble(13.4D)).isEqualTo(13.4);

    assertThatThrownBy(() -> ObjectFunctions.convertToDouble("String"))
        .isExactlyInstanceOf(UnexpectedException.class);
  }

  @Test
  public void testEqualsAnyOf_whenTwoOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 3)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenThreeOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenFourOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3, 4)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3, 4)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1, 4)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenFiveOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3, 4, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3, 4, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1, 4, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 1, 5)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, null)).isTrue();
  }

  @Test
  public void testEqualsAnyOf_whenSixOptions() {
    assertThat(ObjectFunctions.equalsAnyOf(1, 1, 2, 3, 4, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 1, 3, 4, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 1, 4, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 1, 5, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 1, 6)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6, 1)).isTrue();
    assertThat(ObjectFunctions.equalsAnyOf(1, 2, 3, 4, 5, 6, 7)).isFalse();

    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6, 7)).isFalse();
    assertThat(ObjectFunctions.equalsAnyOf(null, 2, 3, 4, 5, 6, null)).isTrue();
  }
}
