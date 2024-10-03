package intellispaces.common.base.type;

import intellispaces.common.base.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link TypeFunctions}.
 */
public class TypeFunctionsTest {

  @Test
  public void testIsAbstractClass() {
    assertThat(TypeFunctions.isAbstractClass(Number.class)).isTrue();
    assertThat(TypeFunctions.isAbstractClass(Integer.class)).isFalse();
  }

  @Test
  public void testIsFinalClass() {
    assertThat(TypeFunctions.isFinalClass(Number.class)).isFalse();
    assertThat(TypeFunctions.isFinalClass(Integer.class)).isTrue();
  }

  @Test
  public void testGetClass() {
    assertThat(TypeFunctions.getClass("java.lang.String")).contains(String.class);
    assertThat(TypeFunctions.getClass("java.lang.String12345")).isEmpty();
  }

  @Test
  public void testGetClassOrElseThrow() {
    assertThat(TypeFunctions.getClassOrElseThrow("java.lang.String")).isSameAs(String.class);
    assertThatThrownBy(() -> TypeFunctions.getClassOrElseThrow("java.lang.String12345"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Could not to get class by name java.lang.String12345");
  }

  @Test
  public void testGetClassOrElseThrowWithExceptionSupplier() {
    assertThat(TypeFunctions.getClassOrElseThrow("java.lang.String", RuntimeException::new)).isSameAs(String.class);
    assertThatThrownBy(() -> TypeFunctions.getClassOrElseThrow("java.lang.String12345", RuntimeException::new))
        .isExactlyInstanceOf(RuntimeException.class);
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
    assertThat(TypeFunctions.getSimpleName("java.util.Map$Entry")).isEqualTo("Entry");
  }

  @Test
  public void testGetPackageName() {
    assertThatThrownBy(() -> TypeFunctions.getPackageName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> TypeFunctions.getPackageName("")).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThat(TypeFunctions.getPackageName("Object")).isEqualTo("");
    assertThat(TypeFunctions.getPackageName("java.lang.Object")).isEqualTo("java.lang");
    assertThat(TypeFunctions.getPackageName("java.lang.Map$Entry")).isEqualTo("java.lang");
  }

  @Test
  public void testShortenName() {
    assertThat(TypeFunctions.shortenName("Object")).isEqualTo("Object");
    assertThat(TypeFunctions.shortenName("java.lang.Object")).isEqualTo("Object");
    assertThat(TypeFunctions.shortenName("java.lang.Map$Entry")).isEqualTo("Entry");
    assertThat(TypeFunctions.shortenName(TypeFunctionsTest.class.getCanonicalName())).isEqualTo(
        TypeFunctionsTest.class.getCanonicalName());
  }

  @Test
  public void testJoinPackageAndSimpleName() {
    assertThatThrownBy(() -> TypeFunctions.joinPackageAndSimpleName("", null)).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThat(TypeFunctions.joinPackageAndSimpleName(null, "SomeClass")).isEqualTo("SomeClass");
    assertThat(TypeFunctions.joinPackageAndSimpleName("", "SomeClass")).isEqualTo("SomeClass");
    assertThat(TypeFunctions.joinPackageAndSimpleName("a", "SomeClass")).isEqualTo("a.SomeClass");
    assertThat(TypeFunctions.joinPackageAndSimpleName("a.b", "SomeClass")).isEqualTo("a.b.SomeClass");
    assertThat(TypeFunctions.joinPackageAndSimpleName("a.b.c", "SomeClass")).isEqualTo("a.b.c.SomeClass");
  }

  @Test
  public void testReplaceSimpleName() {
    assertThatThrownBy(() -> TypeFunctions.replaceSimpleName(null, "New")).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> TypeFunctions.replaceSimpleName("", "New")).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> TypeFunctions.replaceSimpleName("com", null)).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> TypeFunctions.replaceSimpleName("com", "")).isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThat(TypeFunctions.replaceSimpleName("com", "New")).isEqualTo("New");
    assertThat(TypeFunctions.replaceSimpleName("com.Old", "New")).isEqualTo("com.New");
    assertThat(TypeFunctions.replaceSimpleName("com.app.Old", "New")).isEqualTo("com.app.New");
  }

  @Test
  public void testAddPrefixToSimpleName() {
    assertThat(TypeFunctions.addPrefixToSimpleName("Prefix", "Object")).isEqualTo("PrefixObject");
    assertThat(TypeFunctions.addPrefixToSimpleName("Prefix", "java.lang.Object")).isEqualTo("java.lang.PrefixObject");
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
    assertThat(TypeFunctions.getPrimitiveWrapperClass("char")).isEqualTo(Character.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("byte")).isEqualTo(Byte.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("short")).isEqualTo(Short.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("int")).isEqualTo(Integer.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("long")).isEqualTo(Long.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("float")).isEqualTo(Float.class);
    assertThat(TypeFunctions.getPrimitiveWrapperClass("double")).isEqualTo(Double.class);
  }

  @Test
  public void testGetPrimitiveTypeOfWrapper() {
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Boolean.class.getCanonicalName())).isEqualTo("boolean");
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Character.class.getCanonicalName())).isEqualTo("char");
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Byte.class.getCanonicalName())).isEqualTo("byte");
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Short.class.getCanonicalName())).isEqualTo("short");
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Integer.class.getCanonicalName())).isEqualTo("int");
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Long.class.getCanonicalName())).isEqualTo("long");
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Float.class.getCanonicalName())).isEqualTo("float");
    assertThat(TypeFunctions.getPrimitiveTypeOfWrapper(Double.class.getCanonicalName())).isEqualTo("double");

    assertThatThrownBy(() -> TypeFunctions.getPrimitiveTypeOfWrapper(Object.class.getCanonicalName()))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> TypeFunctions.getPrimitiveTypeOfWrapper(String.class.getCanonicalName()))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> TypeFunctions.getPrimitiveTypeOfWrapper(Number.class.getCanonicalName()))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
  }

  @Test
  public void testIsPrimitiveWrapperClass() {
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Boolean.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Character.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Byte.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Short.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Integer.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Long.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Float.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Double.class.getCanonicalName())).isTrue();

    assertThat(TypeFunctions.isPrimitiveWrapperClass(Object.class.getCanonicalName())).isFalse();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(String.class.getCanonicalName())).isFalse();
    assertThat(TypeFunctions.isPrimitiveWrapperClass(Number.class.getCanonicalName())).isFalse();
  }

  @Test
  public void testGetParents() {
    assertThat(TypeFunctions.getParents(Closeable.class)).contains(AutoCloseable.class);
    assertThat(TypeFunctions.getParents(InputStream.class)).contains(Closeable.class);
    assertThat(TypeFunctions.getParents(ByteArrayInputStream.class)).contains(InputStream.class);
  }

  @Test
  public void testNewInstance() {
    ClassWithDefaultConstructor instance = TypeFunctions.newInstance(ClassWithDefaultConstructor.class);
    assertThat(instance).isNotNull();

    assertThatThrownBy(() -> TypeFunctions.newInstance(ClassWithoutDefaultConstructor.class))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Class %s does not contain default constructor without parameters",
            ClassWithoutDefaultConstructor.class.getCanonicalName());
  }

  @Test
  public void testIsDefaultClass() {
    assertThat(TypeFunctions.isDefaultClass(Object.class)).isTrue();
    assertThat(TypeFunctions.isDefaultClass(String.class)).isTrue();
    assertThat(TypeFunctions.isDefaultClass(Integer.class)).isTrue();

    assertThat(TypeFunctions.isDefaultClass(Stream.class)).isFalse();
    assertThat(TypeFunctions.isDefaultClass(Annotation.class)).isFalse();
  }

  @Test
  public void testIsDefaultClassName() {
    assertThat(TypeFunctions.isDefaultClassName(Object.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isDefaultClassName(String.class.getCanonicalName())).isTrue();
    assertThat(TypeFunctions.isDefaultClassName(Integer.class.getCanonicalName())).isTrue();

    assertThat(TypeFunctions.isDefaultClassName(Stream.class.getCanonicalName())).isFalse();
    assertThat(TypeFunctions.isDefaultClassName(Annotation.class.getCanonicalName())).isFalse();
  }

  private static class ClassWithDefaultConstructor {
    public ClassWithDefaultConstructor() {
    }
  }

  private static class ClassWithoutDefaultConstructor {
    public ClassWithoutDefaultConstructor(String string) {
    }
  }
}
