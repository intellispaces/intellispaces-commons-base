package tech.intellispaces.general.object;

import org.junit.jupiter.api.Test;
import tech.intellispaces.general.exception.UnexpectedException;
import tech.intellispaces.general.sample.ClassWithDefaultConstructor;
import tech.intellispaces.general.sample.ClassWithDefaultConstructorThatThrowException;
import tech.intellispaces.general.sample.ClassWithoutDefaultConstructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link Objects} class.
 */
public class ObjectsTest {

  @Test
  public void testGet_whenHasDefaultConstructor() {
    ClassWithDefaultConstructor instance = Objects.get(ClassWithDefaultConstructor.class);
    assertThat(instance).isNotNull();
  }

  @Test
  public void testGet_whenHasNotDefaultConstructor() {
    assertThatThrownBy(() -> Objects.get(ClassWithoutDefaultConstructor.class))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Class %s does not contain default constructor without parameters",
            ClassWithoutDefaultConstructor.class.getCanonicalName());
  }

  @Test
  public void testGet_whenHasDefaultConstructorThatThrowException() {
    assertThatThrownBy(() -> Objects.get(ClassWithDefaultConstructorThatThrowException.class))
        .isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Failed to create instance of the class %s",
            ClassWithDefaultConstructorThatThrowException.class.getCanonicalName());
  }

  @Test
  public void testGetVoid() {
    assertThat(Objects.getVoid()).isNull();
  }
}
