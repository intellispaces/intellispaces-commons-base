package intellispaces.common.base.sample;

import intellispaces.common.base.exception.PossibleViolationException;

public interface ThrowingFunctions {

  static Character throwingCheckedFunction(String string) throws PossibleViolationException {
    if (string.isEmpty()) {
      throw PossibleViolationException.withMessage("Empty string");
    }
    return string.charAt(0);
  }

  static void throwingCheckedConsumer(String string) throws PossibleViolationException {
    if (string.isEmpty()) {
      throw PossibleViolationException.withMessage("Empty string");
    }
  }

  static Character throwingUncheckedFunction(String string) {
    if (string.isEmpty()) {
      throw new RuntimeException("Empty string");
    }
    return string.charAt(0);
  }

  static void throwingUncheckedConsumer(String string) {
    if (string.isEmpty()) {
      throw new RuntimeException("Empty string");
    }
  }
}
