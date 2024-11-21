package tech.intellispaces.entity.type;

import java.util.List;

/**
 * The actual describable type of data or entity.
 *
 * @param <T> associated type.
 */
public interface Type<T> {

  /**
   * Base class.
   */
  Class<?> baseClass();

  /**
   * Type qualifiers.
   */
  List<Type<?>> qualifierTypes();
}
