package tech.intellispaces.entities.object;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tech.intellispaces.entities.sample.ClassWithDefaultConstructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

/**
 * Tests for {@link Objects} class.
 */
public class ObjectsTest {
  private MockedStatic<ObjectFunctions> objectFunctions;

  @BeforeEach
  public void init() {
    objectFunctions = mockStatic(ObjectFunctions.class);
  }

  @AfterEach
  public void deinit() {
    objectFunctions.close();
  }

  @Test
  public void testGet() {
    // Given
    var aClass = ClassWithDefaultConstructor.class;
    var instance = new ClassWithDefaultConstructor();

    objectFunctions.when(() -> ObjectFunctions.newInstance(aClass)).thenReturn(instance);

    // When
    ClassWithDefaultConstructor result = Objects.get(aClass);

    // Then
    objectFunctions.verify(() -> ObjectFunctions.newInstance(aClass));
    assertThat(result).isSameAs(instance);
  }

  @Test
  public void testGetVoid() {
    assertThat(Objects.getVoid()).isNull();
  }
}
