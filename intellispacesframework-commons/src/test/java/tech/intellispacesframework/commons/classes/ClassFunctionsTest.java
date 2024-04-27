package tech.intellispacesframework.commons.classes;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ClassFunctions}.
 */
public class ClassFunctionsTest {

  @Test
  public void testGetSimpleName() {
    assertThatThrownBy(() -> ClassFunctions.getSimpleName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> ClassFunctions.getSimpleName("")).isExactlyInstanceOf(UnexpectedViolationException.class);

    assertThat(ClassFunctions.getSimpleName("Object")).isEqualTo("Object");
    assertThat(ClassFunctions.getSimpleName("java.lang.Object")).isEqualTo("Object");
  }

  @Test
  public void testGetPackageName() {
    assertThatThrownBy(() -> ClassFunctions.getPackageName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> ClassFunctions.getPackageName("")).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThat(ClassFunctions.getPackageName("Object")).isEqualTo("");
    assertThat(ClassFunctions.getPackageName("java.lang.Object")).isEqualTo("java.lang");
  }

  @Test
  public void testGetObjectClass() {
    assertThat(ClassFunctions.getObjectClass(boolean.class)).isSameAs(Boolean.class);
    assertThat(ClassFunctions.getObjectClass(Boolean.class)).isSameAs(Boolean.class);

    assertThat(ClassFunctions.getObjectClass(byte.class)).isSameAs(Byte.class);
    assertThat(ClassFunctions.getObjectClass(Byte.class)).isSameAs(Byte.class);

    assertThat(ClassFunctions.getObjectClass(short.class)).isSameAs(Short.class);
    assertThat(ClassFunctions.getObjectClass(Short.class)).isSameAs(Short.class);

    assertThat(ClassFunctions.getObjectClass(int.class)).isSameAs(Integer.class);
    assertThat(ClassFunctions.getObjectClass(Integer.class)).isSameAs(Integer.class);

    assertThat(ClassFunctions.getObjectClass(long.class)).isSameAs(Long.class);
    assertThat(ClassFunctions.getObjectClass(Long.class)).isSameAs(Long.class);

    assertThat(ClassFunctions.getObjectClass(float.class)).isSameAs(Float.class);
    assertThat(ClassFunctions.getObjectClass(Float.class)).isSameAs(Float.class);

    assertThat(ClassFunctions.getObjectClass(double.class)).isSameAs(Double.class);
    assertThat(ClassFunctions.getObjectClass(Double.class)).isSameAs(Double.class);

    assertThat(ClassFunctions.getObjectClass(void.class)).isSameAs(Void.class);
    assertThat(ClassFunctions.getObjectClass(Void.class)).isSameAs(Void.class);

    assertThat(ClassFunctions.getObjectClass(char.class)).isSameAs(Character.class);
    assertThat(ClassFunctions.getObjectClass(Character.class)).isSameAs(Character.class);

    assertThat(ClassFunctions.getObjectClass(String.class)).isSameAs(String.class);

    assertThat(ClassFunctions.getObjectClass(Object.class)).isSameAs(Object.class);
  }
}
