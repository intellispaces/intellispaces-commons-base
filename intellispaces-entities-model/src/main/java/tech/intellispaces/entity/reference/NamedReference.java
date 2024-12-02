package tech.intellispaces.entity.reference;

/**
 * The named reference.
 *
 * @param <T> the reference target type.
 */
public interface NamedReference<T> extends Reference<T> {

  /**
   * The reference name.
   */
  String name();

  boolean is(String name);
}
