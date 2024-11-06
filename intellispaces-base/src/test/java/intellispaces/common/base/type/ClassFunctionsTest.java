package intellispaces.common.base.type;

import intellispaces.common.base.exception.UnexpectedViolationException;
import intellispaces.common.base.sample.StringToStringFunctionImpl2;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ClassFunctions} class.
 */
public class ClassFunctionsTest {

  @Test
  public void testIsAbstractClass() {
    assertThat(ClassFunctions.isAbstractClass(Number.class)).isTrue();
    assertThat(ClassFunctions.isAbstractClass(Integer.class)).isFalse();
  }

  @Test
  public void testIsFinalClass() {
    assertThat(ClassFunctions.isFinalClass(Number.class)).isFalse();
    assertThat(ClassFunctions.isFinalClass(Integer.class)).isTrue();
  }

  @Test
  public void testGetClass() {
    assertThat(ClassFunctions.getClass("java.lang.String")).contains(String.class);
    assertThat(ClassFunctions.getClass("java.lang.String12345")).isEmpty();
  }

  @Test
  public void testGetClassOrElseThrow() {
    assertThat(ClassFunctions.getClassOrElseThrow("java.lang.String")).isSameAs(String.class);
    assertThatThrownBy(() -> ClassFunctions.getClassOrElseThrow("java.lang.String12345"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Could not to get class by name java.lang.String12345");
  }

  @Test
  public void testGetClassOrElseThrowWithExceptionSupplier() {
    assertThat(ClassFunctions.getClassOrElseThrow("java.lang.String", RuntimeException::new)).isSameAs(String.class);
    assertThatThrownBy(() -> ClassFunctions.getClassOrElseThrow("java.lang.String12345", RuntimeException::new))
        .isExactlyInstanceOf(RuntimeException.class);
  }

  @Test
  public void testHasAnnotationDeep() {
    assertThat(ClassFunctions.hasAnnotationDeep(String.class, FunctionalInterface.class)).isFalse();
    assertThat(ClassFunctions.hasAnnotationDeep(StringToStringFunctionImpl2.class, FunctionalInterface.class)).isTrue();
  }

  @Test
  public void testGetJavaLibraryName() {
    assertThat(ClassFunctions.getJavaLibraryName(ClassFunctions.class)).isPresent();
    assertThat(ClassFunctions.getJavaLibraryName(ClassFunctions.class).orElseThrow()).isNotBlank();
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

    assertThat(ClassFunctions.getObjectClass(int[].class)).isSameAs(int[].class);
  }

  @Test
  public void testGetDefaultValueOf() {
    assertThat(ClassFunctions.getDefaultValueOf(boolean.class)).isEqualTo(false);
    assertThat(ClassFunctions.getDefaultValueOf(char.class)).isEqualTo('\u0000');
    assertThat(ClassFunctions.getDefaultValueOf(byte.class)).isEqualTo((byte) 0);
    assertThat(ClassFunctions.getDefaultValueOf(short.class)).isEqualTo((short) 0);
    assertThat(ClassFunctions.getDefaultValueOf(int.class)).isEqualTo(0);
    assertThat(ClassFunctions.getDefaultValueOf(long.class)).isEqualTo(0L);
    assertThat(ClassFunctions.getDefaultValueOf(float.class)).isEqualTo(0.0f);
    assertThat(ClassFunctions.getDefaultValueOf(double.class)).isEqualTo(0.0);

    assertThat(ClassFunctions.getDefaultValueOf(Boolean.class)).isEqualTo(false);
    assertThat(ClassFunctions.getDefaultValueOf(Character.class)).isEqualTo('\u0000');
    assertThat(ClassFunctions.getDefaultValueOf(Byte.class)).isEqualTo((byte) 0);
    assertThat(ClassFunctions.getDefaultValueOf(Short.class)).isEqualTo((short) 0);
    assertThat(ClassFunctions.getDefaultValueOf(Integer.class)).isEqualTo(0);
    assertThat(ClassFunctions.getDefaultValueOf(Long.class)).isEqualTo(0L);
    assertThat(ClassFunctions.getDefaultValueOf(Float.class)).isEqualTo(0.0f);
    assertThat(ClassFunctions.getDefaultValueOf(Double.class)).isEqualTo(0.0);

    assertThat(ClassFunctions.getDefaultValueOf(String.class)).isNull();
    assertThat(ClassFunctions.getDefaultValueOf(Class.class)).isNull();
  }

  @Test
  public void testGetPrimitiveWrapperClass() {
    assertThat(ClassFunctions.getPrimitiveWrapperClass("boolean")).isEqualTo(Boolean.class);
    assertThat(ClassFunctions.getPrimitiveWrapperClass("char")).isEqualTo(Character.class);
    assertThat(ClassFunctions.getPrimitiveWrapperClass("byte")).isEqualTo(Byte.class);
    assertThat(ClassFunctions.getPrimitiveWrapperClass("short")).isEqualTo(Short.class);
    assertThat(ClassFunctions.getPrimitiveWrapperClass("int")).isEqualTo(Integer.class);
    assertThat(ClassFunctions.getPrimitiveWrapperClass("long")).isEqualTo(Long.class);
    assertThat(ClassFunctions.getPrimitiveWrapperClass("float")).isEqualTo(Float.class);
    assertThat(ClassFunctions.getPrimitiveWrapperClass("double")).isEqualTo(Double.class);

    assertThatThrownBy(() -> ClassFunctions.getPrimitiveWrapperClass("String"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Not primitive typename: String");
  }

  @Test
  public void testGetPrimitiveTypeOfWrapper() {
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Boolean.class.getCanonicalName())).isEqualTo("boolean");
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Character.class.getCanonicalName())).isEqualTo("char");
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Byte.class.getCanonicalName())).isEqualTo("byte");
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Short.class.getCanonicalName())).isEqualTo("short");
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Integer.class.getCanonicalName())).isEqualTo("int");
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Long.class.getCanonicalName())).isEqualTo("long");
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Float.class.getCanonicalName())).isEqualTo("float");
    assertThat(ClassFunctions.getPrimitiveTypeOfWrapper(Double.class.getCanonicalName())).isEqualTo("double");

    assertThatThrownBy(() -> ClassFunctions.getPrimitiveTypeOfWrapper(Object.class.getCanonicalName()))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> ClassFunctions.getPrimitiveTypeOfWrapper(String.class.getCanonicalName()))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
    assertThatThrownBy(() -> ClassFunctions.getPrimitiveTypeOfWrapper(Number.class.getCanonicalName()))
        .isExactlyInstanceOf(UnexpectedViolationException.class);
  }

  @Test
  public void testPrimitiveByWrapperClassName() {
    assertThat(ClassFunctions.primitiveByWrapperClassName(Boolean.class.getCanonicalName())).contains(Primitives.Boolean);
    assertThat(ClassFunctions.primitiveByWrapperClassName(Character.class.getCanonicalName())).contains(Primitives.Char);
    assertThat(ClassFunctions.primitiveByWrapperClassName(Byte.class.getCanonicalName())).contains(Primitives.Byte);
    assertThat(ClassFunctions.primitiveByWrapperClassName(Short.class.getCanonicalName())).contains(Primitives.Short);
    assertThat(ClassFunctions.primitiveByWrapperClassName(Integer.class.getCanonicalName())).contains(Primitives.Int);
    assertThat(ClassFunctions.primitiveByWrapperClassName(Long.class.getCanonicalName())).contains(Primitives.Long);
    assertThat(ClassFunctions.primitiveByWrapperClassName(Float.class.getCanonicalName())).contains(Primitives.Float);
    assertThat(ClassFunctions.primitiveByWrapperClassName(Double.class.getCanonicalName())).contains(Primitives.Double);

    assertThat(ClassFunctions.primitiveByWrapperClassName(boolean.class.getCanonicalName())).isEmpty();
    assertThat(ClassFunctions.primitiveByWrapperClassName(char.class.getCanonicalName())).isEmpty();
    assertThat(ClassFunctions.primitiveByWrapperClassName(byte.class.getCanonicalName())).isEmpty();
    assertThat(ClassFunctions.primitiveByWrapperClassName(short.class.getCanonicalName())).isEmpty();
    assertThat(ClassFunctions.primitiveByWrapperClassName(int.class.getCanonicalName())).isEmpty();
    assertThat(ClassFunctions.primitiveByWrapperClassName(long.class.getCanonicalName())).isEmpty();
    assertThat(ClassFunctions.primitiveByWrapperClassName(float.class.getCanonicalName())).isEmpty();
    assertThat(ClassFunctions.primitiveByWrapperClassName(double.class.getCanonicalName())).isEmpty();

    assertThat(ClassFunctions.primitiveByWrapperClassName(String.class.getCanonicalName())).isEmpty();
  }

  @Test
  public void testIsPrimitiveWrapperClass() {
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Boolean.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Character.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Byte.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Short.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Integer.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Long.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Float.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Double.class.getCanonicalName())).isTrue();

    assertThat(ClassFunctions.isPrimitiveWrapperClass(Object.class.getCanonicalName())).isFalse();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(String.class.getCanonicalName())).isFalse();
    assertThat(ClassFunctions.isPrimitiveWrapperClass(Number.class.getCanonicalName())).isFalse();
  }

  @Test
  public void testIsBooleanClass() {
    assertThat(ClassFunctions.isBooleanClass(Boolean.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isBooleanClass(boolean.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isBooleanClass(String.class.getCanonicalName())).isFalse();
  }

  @Test
  public void testIsDoubleClass() {
    assertThat(ClassFunctions.isDoubleClass(Double.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isDoubleClass(double.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isDoubleClass(String.class.getCanonicalName())).isFalse();
  }

  @Test
  public void testGetParents() {
    assertThat(ClassFunctions.getParents(Closeable.class)).contains(AutoCloseable.class);
    assertThat(ClassFunctions.getParents(InputStream.class)).contains(Closeable.class);
    assertThat(ClassFunctions.getParents(ByteArrayInputStream.class)).contains(InputStream.class);
  }

  @Test
  public void testIsStandardClass() {
    assertThat(ClassFunctions.isStandardClass(Object.class)).isTrue();
    assertThat(ClassFunctions.isStandardClass(String.class)).isTrue();
    assertThat(ClassFunctions.isStandardClass(Integer.class)).isTrue();

    assertThat(ClassFunctions.isStandardClass(Stream.class)).isFalse();
    assertThat(ClassFunctions.isStandardClass(Annotation.class)).isFalse();
  }

  @Test
  public void testIsStandardClassName() {
    assertThat(ClassFunctions.isStandardClassName(Object.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isStandardClassName(String.class.getCanonicalName())).isTrue();
    assertThat(ClassFunctions.isStandardClassName(Integer.class.getCanonicalName())).isTrue();

    assertThat(ClassFunctions.isStandardClassName(Stream.class.getCanonicalName())).isFalse();
    assertThat(ClassFunctions.isStandardClassName(Annotation.class.getCanonicalName())).isFalse();
  }
}
