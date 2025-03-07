package tech.intellispaces.commons.type;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link MethodFunctions} class.
 */
public class MethodFunctionsTest {

  @Test
  public void testIsAbstractMethod() throws Exception {
    assertThat(MethodFunctions.isAbstractMethod(Number.class.getDeclaredMethod("intValue"))).isTrue();
    assertThat(MethodFunctions.isAbstractMethod(Integer.class.getDeclaredMethod("intValue"))).isFalse();
  }

  @Test
  public void testIsPublicMethod() throws Exception {
    assertThat(MethodFunctions.isPublicMethod(Object.class.getDeclaredMethod("clone"))).isFalse();
    assertThat(MethodFunctions.isPublicMethod(ArrayList.class.getDeclaredMethod("clone"))).isTrue();
  }

  @Test
  public void testIsStaticMethod() throws Exception {
    assertThat(MethodFunctions.isStaticMethod(List.class.getDeclaredMethod("of"))).isTrue();
    assertThat(MethodFunctions.isStaticMethod(ArrayList.class.getDeclaredMethod("get", int.class))).isFalse();
  }

  @Test
  public void testGetMethod_whenNoParams() {
    assertThat(MethodFunctions.getMethod(String.class, "trim")).isPresent();
    assertThat(MethodFunctions.getMethod(String.class, "trim123")).isNotPresent();
  }

  @Test
  public void testGetMethod_whenParams() {
    assertThat(MethodFunctions.getMethod(String.class, "charAt", int.class)).isPresent();
    assertThat(MethodFunctions.getMethod(String.class, "charAt", long.class)).isNotPresent();
  }
}
