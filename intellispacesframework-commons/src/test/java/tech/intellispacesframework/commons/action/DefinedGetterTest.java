package tech.intellispacesframework.commons.action;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DefinedGetter} class.
 */
public class DefinedGetterTest {

  @Test
  public void test_whenDefaultConstructor() {
    var getter = new DefinedGetter<String>();
    assertThat(getter.get()).isNull();

    getter.set("value1");
    assertThat(getter.get()).isEqualTo("value1");

    getter.set("value2");
    assertThat(getter.get()).isEqualTo("value2");
  }

  @Test
  public void test_whenInitConstructor() {
    var getter = new DefinedGetter<String>("value1");
    assertThat(getter.get()).isEqualTo("value1");

    getter.set("value2");
    assertThat(getter.get()).isEqualTo("value2");

    getter.set("value3");
    assertThat(getter.get()).isEqualTo("value3");
  }
}
