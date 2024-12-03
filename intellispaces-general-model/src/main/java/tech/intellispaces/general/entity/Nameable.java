package tech.intellispaces.general.entity;

/**
 * The name referencable entity.
 */
public interface Nameable {

  /**
   * The related name.
   */
  String name();

  boolean is(String name);
}
