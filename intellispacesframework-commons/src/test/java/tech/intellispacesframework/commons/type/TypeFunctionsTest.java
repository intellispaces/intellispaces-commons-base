package tech.intellispacesframework.commons.type;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link TypeFunctions}.
 */
public class TypeFunctionsTest {

  @Test
  public void testGetSimpleName() {
    assertThatThrownBy(() -> TypeFunctions.getSimpleName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> TypeFunctions.getSimpleName("")).isExactlyInstanceOf(UnexpectedViolationException.class);

    assertThat(TypeFunctions.getSimpleName("Object")).isEqualTo("Object");
    assertThat(TypeFunctions.getSimpleName("java.lang.Object")).isEqualTo("Object");
  }

  @Test
  public void testGetPackageName() {
    assertThatThrownBy(() -> TypeFunctions.getPackageName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> TypeFunctions.getPackageName("")).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThat(TypeFunctions.getPackageName("Object")).isEqualTo("");
    assertThat(TypeFunctions.getPackageName("java.lang.Object")).isEqualTo("java.lang");
  }

  @Test
  public void testGetObjectClass() {
    assertThat(TypeFunctions.getObjectClass(boolean.class)).isSameAs(Boolean.class);
    assertThat(TypeFunctions.getObjectClass(Boolean.class)).isSameAs(Boolean.class);

    assertThat(TypeFunctions.getObjectClass(byte.class)).isSameAs(Byte.class);
    assertThat(TypeFunctions.getObjectClass(Byte.class)).isSameAs(Byte.class);

    assertThat(TypeFunctions.getObjectClass(short.class)).isSameAs(Short.class);
    assertThat(TypeFunctions.getObjectClass(Short.class)).isSameAs(Short.class);

    assertThat(TypeFunctions.getObjectClass(int.class)).isSameAs(Integer.class);
    assertThat(TypeFunctions.getObjectClass(Integer.class)).isSameAs(Integer.class);

    assertThat(TypeFunctions.getObjectClass(long.class)).isSameAs(Long.class);
    assertThat(TypeFunctions.getObjectClass(Long.class)).isSameAs(Long.class);

    assertThat(TypeFunctions.getObjectClass(float.class)).isSameAs(Float.class);
    assertThat(TypeFunctions.getObjectClass(Float.class)).isSameAs(Float.class);

    assertThat(TypeFunctions.getObjectClass(double.class)).isSameAs(Double.class);
    assertThat(TypeFunctions.getObjectClass(Double.class)).isSameAs(Double.class);

    assertThat(TypeFunctions.getObjectClass(void.class)).isSameAs(Void.class);
    assertThat(TypeFunctions.getObjectClass(Void.class)).isSameAs(Void.class);

    assertThat(TypeFunctions.getObjectClass(char.class)).isSameAs(Character.class);
    assertThat(TypeFunctions.getObjectClass(Character.class)).isSameAs(Character.class);

    assertThat(TypeFunctions.getObjectClass(String.class)).isSameAs(String.class);

    assertThat(TypeFunctions.getObjectClass(Object.class)).isSameAs(Object.class);
  }

  @Test
  public void testGetAnyValidValueOfClass() {
    assertThat(TypeFunctions.getAnyValidValueOfClass(boolean.class)).isEqualTo(false);
    assertThat(TypeFunctions.getAnyValidValueOfClass(char.class)).isEqualTo('\u0000');
    assertThat(TypeFunctions.getAnyValidValueOfClass(byte.class)).isEqualTo((byte) 0);
    assertThat(TypeFunctions.getAnyValidValueOfClass(short.class)).isEqualTo((short) 0);
    assertThat(TypeFunctions.getAnyValidValueOfClass(int.class)).isEqualTo(0);
    assertThat(TypeFunctions.getAnyValidValueOfClass(long.class)).isEqualTo(0L);
    assertThat(TypeFunctions.getAnyValidValueOfClass(float.class)).isEqualTo(0.0f);
    assertThat(TypeFunctions.getAnyValidValueOfClass(double.class)).isEqualTo(0.0);

    assertThat(TypeFunctions.getAnyValidValueOfClass(Boolean.class)).isEqualTo(false);
    assertThat(TypeFunctions.getAnyValidValueOfClass(Character.class)).isEqualTo('\u0000');
    assertThat(TypeFunctions.getAnyValidValueOfClass(Byte.class)).isEqualTo((byte) 0);
    assertThat(TypeFunctions.getAnyValidValueOfClass(Short.class)).isEqualTo((short) 0);
    assertThat(TypeFunctions.getAnyValidValueOfClass(Integer.class)).isEqualTo(0);
    assertThat(TypeFunctions.getAnyValidValueOfClass(Long.class)).isEqualTo(0L);
    assertThat(TypeFunctions.getAnyValidValueOfClass(Float.class)).isEqualTo(0.0f);
    assertThat(TypeFunctions.getAnyValidValueOfClass(Double.class)).isEqualTo(0.0);

    assertThat(TypeFunctions.getAnyValidValueOfClass(String.class)).isNull();
    assertThat(TypeFunctions.getAnyValidValueOfClass(Class.class)).isNull();
  }
}
