package intellispaces.common.base.type;

import intellispaces.common.base.exception.UnexpectedViolationException;

/**
 * Reference to something.
 *
 * @param <T> reference target type.
 */
public interface Reference<T> {

  /**
   * Reference target.
   */
  T get();

  /**
   * Ordinal representation of the reference.
   */
  default int asOrdinal() {
    throw UnexpectedViolationException.withMessage("This reference cannot be represented as an ordinal value");
  }

  /**
   * String key representation of the reference.
   */
  default String asKey() {
    throw UnexpectedViolationException.withMessage("This reference cannot be represented as an string key");
  }
}
