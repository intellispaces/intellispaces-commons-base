package tech.intellispaces.commons.basic.type;

import javax.lang.model.element.Element;

/**
 * Functions related to Java Model elements.
 */
public class ElementFunctions {

  public static boolean isAbstractElement(Element element) {
    return element.getModifiers().contains(javax.lang.model.element.Modifier.ABSTRACT);
  }

  public static boolean isFinalElement(Element element) {
    return element.getModifiers().contains(javax.lang.model.element.Modifier.FINAL);
  }
}
