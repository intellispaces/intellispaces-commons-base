package intellispaces.common.base.type;

import intellispaces.common.base.data.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

/**
 * Test for {@link Classes} class.
 */
public class ClassesTest {

  @Test
  public void testGet() {
    try (MockedStatic<ClassFunctions> classFunctions = mockStatic(ClassFunctions.class)) {
      var className = String.class.getCanonicalName();
      var aClass = String.class;
      classFunctions.when(() -> ClassFunctions.getClass(className)).thenReturn(Optional.of(aClass));

      // When
      Optional<Class<?>> result = Classes.get(className);

      // Then
      assertThat(result).containsSame(aClass);
    }
  }

  @Test
  public void testGenericCast() {
    Class<String> stringClass = Classes.genericCast(String.class);
    assertThat(stringClass).isSameAs(String.class);

    Class<Pair<String, Integer>> pairClass = Classes.genericCast(Pair.class);
    assertThat(pairClass).isSameAs(Pair.class);
  }

  @Test
  public void testOfList() {
    Class<List<String>> listClass = Classes.ofList(String.class);
    assertThat(listClass).isSameAs(List.class);
  }
}
