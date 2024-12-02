package tech.intellispaces.entity.reference;

/**
 * The ordinal reference.
 *
 * @param <T> the reference target type.
 */
public interface OrdinalReference<T> extends Reference<T> {

  /**
   * The reference ordinal number.
   */
  int ordinal();

  boolean is(int ordinal);
}
