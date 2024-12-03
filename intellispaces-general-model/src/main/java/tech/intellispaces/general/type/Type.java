package tech.intellispaces.general.type;

import java.util.List;

/**
 * The abstract type of entity.
 *
 * @param <T> associated type.
 */
public interface Type<T> {

  /**
   * The base type.
   */
  Type<?> baseType();

  /**
   * Type qualifiers.
   */
  List<Type<?>> qualifierTypes();

  /**
   * Returns class-based representation of the type or <code>null</code>.
   * <p>
   * Not every type can have a class-based type associated with it.
   */
  ClassType<T> asClassType();
}
