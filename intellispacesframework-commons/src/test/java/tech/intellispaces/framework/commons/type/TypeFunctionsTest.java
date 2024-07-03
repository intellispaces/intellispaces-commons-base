package tech.intellispaces.framework.commons.type;

import org.junit.jupiter.api.Test;
import tech.intellispaces.framework.commons.exception.UnexpectedViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link TypeFunctions}.
 */
public class TypeFunctionsTest {

  @Test
  public void testGetClass() {
    assertThat(TypeFunctions.getClass("java.lang.String")).contains(String.class);
    assertThat(TypeFunctions.getClass("java.lang.String12345")).isEmpty();
  }

  @Test
  public void testGetMethod() {
    assertThat(TypeFunctions.getMethod(String.class, "trim")).isPresent();
    assertThat(TypeFunctions.getMethod(String.class, "trim123")).isNotPresent();
  }

  @Test
  public void testGetJavaLibraryName() {
    assertThat(TypeFunctions.getJavaLibraryName(TypeFunctions.class)).isNotBlank();
  }

  @Test
  public void testGetSimpleName() {
    assertThatThrownBy(() -> TypeFunctions.getSimpleName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> TypeFunctions.getSimpleName("")).isExactlyInstanceOf(UnexpectedViolationException.class);

    assertThat(TypeFunctions.getSimpleName("Object")).isEqualTo("Object");
    assertThat(TypeFunctions.getSimpleName("java.lang.Object")).isEqualTo("Object");
    assertThat(TypeFunctions.getSimpleName("java.util.Map.Entry")).isEqualTo("Entry");
  }

  @Test
  public void testGetPackageName() {
    assertThatThrownBy(() -> TypeFunctions.getPackageName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> TypeFunctions.getPackageName("")).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThat(TypeFunctions.getPackageName("Object")).isEqualTo("");
    assertThat(TypeFunctions.getPackageName("java.lang.Object")).isEqualTo("java.lang");
  }

  @Test
  public void testJoinPackageAndClassname() {
    assertThatThrownBy(() -> TypeFunctions.joinPackageAndClassname("", null)).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThat(TypeFunctions.joinPackageAndClassname(null, "SomeClass")).isEqualTo("SomeClass");
    assertThat(TypeFunctions.joinPackageAndClassname("", "SomeClass")).isEqualTo("SomeClass");
    assertThat(TypeFunctions.joinPackageAndClassname("a", "SomeClass")).isEqualTo("a.SomeClass");
    assertThat(TypeFunctions.joinPackageAndClassname("a.b", "SomeClass")).isEqualTo("a.b.SomeClass");
    assertThat(TypeFunctions.joinPackageAndClassname("a.b.c", "SomeClass")).isEqualTo("a.b.c.SomeClass");

  }

  @Test
  public void testAddPrefixToClassName() {
    assertThat(TypeFunctions.addPrefixToClassName("Prefix", "Object")).isEqualTo("PrefixObject");
    assertThat(TypeFunctions.addPrefixToClassName("Prefix", "java.lang.Object")).isEqualTo("java.lang.PrefixObject");
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
  public void testGetDefaultValueOf() {
    assertThat(TypeFunctions.getDefaultValueOf(boolean.class)).isEqualTo(false);
    assertThat(TypeFunctions.getDefaultValueOf(char.class)).isEqualTo('\u0000');
    assertThat(TypeFunctions.getDefaultValueOf(byte.class)).isEqualTo((byte) 0);
    assertThat(TypeFunctions.getDefaultValueOf(short.class)).isEqualTo((short) 0);
    assertThat(TypeFunctions.getDefaultValueOf(int.class)).isEqualTo(0);
    assertThat(TypeFunctions.getDefaultValueOf(long.class)).isEqualTo(0L);
    assertThat(TypeFunctions.getDefaultValueOf(float.class)).isEqualTo(0.0f);
    assertThat(TypeFunctions.getDefaultValueOf(double.class)).isEqualTo(0.0);

    assertThat(TypeFunctions.getDefaultValueOf(Boolean.class)).isEqualTo(false);
    assertThat(TypeFunctions.getDefaultValueOf(Character.class)).isEqualTo('\u0000');
    assertThat(TypeFunctions.getDefaultValueOf(Byte.class)).isEqualTo((byte) 0);
    assertThat(TypeFunctions.getDefaultValueOf(Short.class)).isEqualTo((short) 0);
    assertThat(TypeFunctions.getDefaultValueOf(Integer.class)).isEqualTo(0);
    assertThat(TypeFunctions.getDefaultValueOf(Long.class)).isEqualTo(0L);
    assertThat(TypeFunctions.getDefaultValueOf(Float.class)).isEqualTo(0.0f);
    assertThat(TypeFunctions.getDefaultValueOf(Double.class)).isEqualTo(0.0);

    assertThat(TypeFunctions.getDefaultValueOf(String.class)).isNull();
    assertThat(TypeFunctions.getDefaultValueOf(Class.class)).isNull();
  }

  @Test
  public void testGetPrimitiveWrapperClass() {
    assertThat(TypeFunctions.getPrimitiveWrapperClass("boolean")).isEqualTo(Boolean.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("byte")).isEqualTo(Byte.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("short")).isEqualTo(Short.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("int")).isEqualTo(Integer.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("long")).isEqualTo(Long.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("float")).isEqualTo(Float.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("double")).isEqualTo(Double.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("char")).isEqualTo(Character.class);
  }
}
