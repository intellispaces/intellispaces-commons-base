package intellispaces.common.base.object;

import intellispaces.common.base.exception.UnexpectedViolationException;
import intellispaces.common.base.sample.ClassWithDefaultConstructor;
import intellispaces.common.base.sample.ClassWithDefaultConstructorThatThrowException;
import intellispaces.common.base.sample.ClassWithoutDefaultConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Class %s does not contain default constructor without parameters",
            ClassWithoutDefaultConstructor.class.getCanonicalName());
  }

  @Test
  public void testNewInstance_whenHasDefaultConstructorThatThrowException() {
    assertThatThrownBy(() -> ObjectFunctions.newInstance(ClassWithDefaultConstructorThatThrowException.class))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Failed to create instance of the class %s",
            ClassWithDefaultConstructorThatThrowException.class.getCanonicalName());
  }

  @Test
  public void testConvertObjectToInt() {
    assertThat(ObjectFunctions.convertToInt('a')).isEqualTo(97);
    assertThat(ObjectFunctions.convertToInt((byte) 13)).isEqualTo(13);
    assertThat(ObjectFunctions.convertToInt((short) 13)).isEqualTo(13);
    assertThat(ObjectFunctions.convertToInt((13))).isEqualTo(13);

    assertThatThrownBy(() -> ObjectFunctions.convertToInt(13L))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> ObjectFunctions.convertToInt(3.14F))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> ObjectFunctions.convertToInt(3.14D))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> ObjectFunctions.convertToInt("String"))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
  }

  @Test
  public void testConvertObjectToDouble() {
    assertThat(ObjectFunctions.convertToDouble('a')).isEqualTo(97.0);
    assertThat(ObjectFunctions.convertToDouble((byte) 13)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble((short) 13)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble((13))).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble(13L)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble(13F)).isEqualTo(13.0);
    assertThat(ObjectFunctions.convertToDouble(13.4D)).isEqualTo(13.4);

    assertThatThrownBy(() -> ObjectFunctions.convertToDouble("String"))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
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

  @Test
  public void testHandle_whenOneHandler() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add);

    // Then
    assertThat(list).containsExactly("abc");
  }

  @Test
  public void testHandle_whenTwoHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc");
  }

  @Test
  public void testHandle_whenThreeHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFourHandlers() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandle_whenFiveHandlesr() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handle("abc", list::add, list::add, list::add, list::add, list::add);

    // Then
    assertThat(list).containsExactly("abc", "abc", "abc", "abc", "abc");
  }

  @Test
  public void testHandleEach_whenTwoValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", list::add);

    // Then
    assertThat(list).containsExactly("a", "b");
  }

  @Test
  public void testHandleEach_whenThreeValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", "c", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c");
  }

  @Test
  public void testHandleEach_whenFourValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", "c", "d", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d");
  }

  @Test
  public void testHandleEach_whenFiveValues() {
    // Given
    List<String> list = new ArrayList<>();

    // When
    ObjectFunctions.handleEach("a", "b", "c", "d", "e", list::add);

    // Then
    assertThat(list).containsExactly("a", "b", "c", "d", "e");
  }

  @Test
  public void testCoalesce_whenSuppliers() {
    assertThat(ObjectFunctions.coalesce(() -> "a", () -> "b")).isEqualTo("a");
    assertThat(ObjectFunctions.coalesce(() -> null, () -> "b")).isEqualTo("b");
    assertThat(ObjectFunctions.coalesce(() -> "a", () -> null)).isEqualTo("a");
    assertThat(ObjectFunctions.coalesce(() -> null, () -> (String) null)).isNull();
  }

  @Test
  public void testCoalesce_whenFunctions() {
    assertThat((String) ObjectFunctions.coalesce(1, n -> "a", n -> "b")).isEqualTo("a");
    assertThat((String) ObjectFunctions.coalesce(1, n -> null, n -> "b")).isEqualTo("b");
    assertThat((String) ObjectFunctions.coalesce(1, n -> "a", n -> null)).isEqualTo("a");
    assertThat((String) ObjectFunctions.coalesce(1, n -> null, n -> null)).isNull();
  }
}
