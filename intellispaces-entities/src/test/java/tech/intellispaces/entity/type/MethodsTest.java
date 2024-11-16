package tech.intellispaces.entity.type;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

/**
 * Tests for {@link Methods} class.
 */
public class MethodsTest {
  private MockedStatic<MethodFunctions> methodFunctions;

  @BeforeEach
  public void init() {
    methodFunctions = mockStatic(MethodFunctions.class);
  }

  @AfterEach
  public void deinit() {
    methodFunctions.close();
  }

  @Test
  public void testGetMethod_whenNoParams() {
    // Given
    var aClass = String.class;
    String methodName = "trim";
    Method method = mock(Method.class);
    methodFunctions.when(() -> MethodFunctions.getMethod(aClass, methodName)).thenReturn(Optional.of(method));

    // When
    Optional<Method> result = Methods.get(aClass, methodName);

    // Then
    assertThat(result).containsSame(method);
  }

  @Test
  public void testGetMethod_whenParams() {
    // Given
    var aClass = String.class;
    String methodName = "charAt";
    var paramClass = int.class;
    Method method = mock(Method.class);
    methodFunctions.when(() -> MethodFunctions.getMethod(aClass, methodName, paramClass)).thenReturn(Optional.of(method));

    // When
    Optional<Method> result = Methods.get(aClass, methodName, paramClass);

    // Then
    assertThat(result).containsSame(method);

  }
}
