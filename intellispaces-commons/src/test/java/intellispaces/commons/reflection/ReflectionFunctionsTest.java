package intellispaces.commons.reflection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ReflectionFunctions} class.
 */
public class ReflectionFunctionsTest {

  @Test
  public void testGetStaticField_whenPrivate() throws Exception {
    // When
    Boolean answer1 = ReflectionFunctions.getStaticField(ClassSample.class, "FIELD1", Boolean.class);
    boolean answer2 = ReflectionFunctions.getStaticField(ClassSample.class, "FIELD2", boolean.class);

    // Then
    assertThat(answer1).isTrue();
    assertThat(answer2).isFalse();
  }

  @Test
  public void testSetStaticField_whenPrivate() throws Exception {
    // When
    ReflectionFunctions.setStaticField(ClassSample.class, "FIELD1", false);
    ReflectionFunctions.setStaticField(ClassSample.class, "FIELD2", true);

    // Then
    assertThat(ClassSample.getField1()).isFalse();
    assertThat(ClassSample.getField2()).isTrue();

    // When
    ReflectionFunctions.setStaticField(ClassSample.class, "FIELD1", true);
    ReflectionFunctions.setStaticField(ClassSample.class, "FIELD2", false);

    // Then
    assertThat(ClassSample.getField1()).isTrue();
    assertThat(ClassSample.getField2()).isFalse();
  }
}
