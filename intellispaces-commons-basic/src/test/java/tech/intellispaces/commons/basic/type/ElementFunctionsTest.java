package tech.intellispaces.commons.basic.type;

import org.junit.jupiter.api.Test;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ElementFunctions} class.
 */
public class ElementFunctionsTest {

  @Test
  public void testIsAbstractElement() {
    var abstractElement = mock(Element.class);
    when(abstractElement.getModifiers()).thenReturn(Set.of(Modifier.ABSTRACT));
    assertThat(ElementFunctions.isAbstractElement(abstractElement)).isTrue();

    var notAbstractElement = mock(Element.class);
    when(notAbstractElement.getModifiers()).thenReturn(Set.of());
    assertThat(ElementFunctions.isAbstractElement(notAbstractElement)).isFalse();
  }

  @Test
  public void testIsFinalElement() {
    var abstractElement = mock(Element.class);
    when(abstractElement.getModifiers()).thenReturn(Set.of(Modifier.FINAL));
    assertThat(ElementFunctions.isFinalElement(abstractElement)).isTrue();

    var notAbstractElement = mock(Element.class);
    when(notAbstractElement.getModifiers()).thenReturn(Set.of());
    assertThat(ElementFunctions.isFinalElement(notAbstractElement)).isFalse();
  }
}
