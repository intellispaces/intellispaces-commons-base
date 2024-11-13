package tech.intellispaces.common.entities.sample;

import tech.intellispaces.common.entities.exception.AssumptionViolationException;
import tech.intellispaces.common.entities.exception.AssumptionViolationExceptions;

public interface ThrowingFunctions {

  static Character throwingCheckedFunction(String string) throws AssumptionViolationException {
    if (string.isEmpty()) {
      throw AssumptionViolationExceptions.withMessage("Empty string");
    }
    return string.charAt(0);
  }

  static void throwingCheckedConsumer(String string) throws AssumptionViolationException {
    if (string.isEmpty()) {
      throw AssumptionViolationExceptions.withMessage("Empty string");
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
