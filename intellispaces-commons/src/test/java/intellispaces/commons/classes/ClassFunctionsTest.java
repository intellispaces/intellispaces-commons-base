package intellispaces.commons.classes;

import intellispaces.commons.exception.UnexpectedViolationException;
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
}
