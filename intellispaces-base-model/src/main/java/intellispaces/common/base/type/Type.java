package intellispaces.common.base.type;

import java.util.List;

public interface Type<T> {

  /**
   * Base class.
   */
  Class<T> baseClass();

  /**
   * Type qualifiers.
   */
  List<Type<?>> qualifierTypes();
}
