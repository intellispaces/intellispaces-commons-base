package tech.intellispaces.entity.type;

import org.junit.jupiter.api.Test;
import tech.intellispaces.entity.exception.UnexpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ClassNameFunctions} class.
 */
public class ClassNameFunctionsTest {

  @Test
  public void testGetSimpleName() {
    assertThatThrownBy(() -> ClassNameFunctions.getSimpleName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> ClassNameFunctions.getSimpleName("")).isExactlyInstanceOf(UnexpectedException.class);

    assertThat(ClassNameFunctions.getSimpleName("Object")).isEqualTo("Object");
    assertThat(ClassNameFunctions.getSimpleName("java.lang.Object")).isEqualTo("Object");
    assertThat(ClassNameFunctions.getSimpleName("java.util.Map.Entry")).isEqualTo("Entry");
    assertThat(ClassNameFunctions.getSimpleName("java.util.Map$Entry")).isEqualTo("Entry");

    assertThat(ClassNameFunctions.getSimpleName("java.util.List<String>")).isEqualTo("List");
    assertThat(ClassNameFunctions.getSimpleName("java.util.Map<String, List<Integer>>")).isEqualTo("Map");
  }

  @Test
  public void testGetPackageName() {
    assertThatThrownBy(() -> ClassNameFunctions.getPackageName(null)).isExactlyInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> ClassNameFunctions.getPackageName("")).isExactlyInstanceOf(UnexpectedException.class);
    assertThat(ClassNameFunctions.getPackageName("Object")).isEqualTo("");
    assertThat(ClassNameFunctions.getPackageName("java.lang.Object")).isEqualTo("java.lang");
    assertThat(ClassNameFunctions.getPackageName("java.lang.Map$Entry")).isEqualTo("java.lang");
  }

  @Test
  public void testGetShortenName() {
    assertThat(ClassNameFunctions.getShortenName("Object")).isEqualTo("Object");
    assertThat(ClassNameFunctions.getShortenName("java.lang.Object")).isEqualTo("Object");
    assertThat(ClassNameFunctions.getShortenName("java.lang.Map$Entry")).isEqualTo("Entry");
    assertThat(ClassNameFunctions.getShortenName(ClassNameFunctions.class.getCanonicalName())).isEqualTo(
        ClassNameFunctions.class.getCanonicalName());
  }

  @Test
  public void testJoinPackageAndSimpleName() {
    assertThatThrownBy(() -> ClassNameFunctions.joinPackageAndSimpleName("", null)).isExactlyInstanceOf(UnexpectedException.class);
    assertThat(ClassNameFunctions.joinPackageAndSimpleName(null, "SomeClass")).isEqualTo("SomeClass");
    assertThat(ClassNameFunctions.joinPackageAndSimpleName("", "SomeClass")).isEqualTo("SomeClass");
    assertThat(ClassNameFunctions.joinPackageAndSimpleName("a", "SomeClass")).isEqualTo("a.SomeClass");
    assertThat(ClassNameFunctions.joinPackageAndSimpleName("a.b", "SomeClass")).isEqualTo("a.b.SomeClass");
    assertThat(ClassNameFunctions.joinPackageAndSimpleName("a.b.c", "SomeClass")).isEqualTo("a.b.c.SomeClass");
  }

  @Test
  public void testReplaceSimpleName() {
    assertThatThrownBy(() -> ClassNameFunctions.replaceSimpleName(null, "New")).isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ClassNameFunctions.replaceSimpleName("", "New")).isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ClassNameFunctions.replaceSimpleName("com", null)).isExactlyInstanceOf(UnexpectedException.class);
    assertThatThrownBy(() -> ClassNameFunctions.replaceSimpleName("com", "")).isExactlyInstanceOf(UnexpectedException.class);
    assertThat(ClassNameFunctions.replaceSimpleName("com", "New")).isEqualTo("New");
    assertThat(ClassNameFunctions.replaceSimpleName("com.Old", "New")).isEqualTo("com.New");
    assertThat(ClassNameFunctions.replaceSimpleName("com.app.Old", "New")).isEqualTo("com.app.New");
  }

  @Test
  public void testAddPrefixToSimpleName() {
    assertThat(ClassNameFunctions.addPrefixToSimpleName("Prefix", "Object")).isEqualTo("PrefixObject");
    assertThat(ClassNameFunctions.addPrefixToSimpleName("Prefix", "java.lang.Object")).isEqualTo("java.lang.PrefixObject");
  }
}
