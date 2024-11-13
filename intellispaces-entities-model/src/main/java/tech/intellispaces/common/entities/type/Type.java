package tech.intellispaces.common.entities.type;

import java.util.List;

/**
 * This interface represents a type.
 *
 * @param <T> associated type.
 */
public interface Type<T> {

  /**
   * Base class of the type.
   */
  Class<?> baseClass();

  /**
   * Type qualifiers.
   */
  List<Type<?>> qualifierTypes();
}
