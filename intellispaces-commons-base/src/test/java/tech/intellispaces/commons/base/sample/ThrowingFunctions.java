package tech.intellispaces.commons.base.sample;

import tech.intellispaces.commons.base.exception.CheckedException;
import tech.intellispaces.commons.base.exception.CheckedExceptions;

public interface ThrowingFunctions {

  static Character throwingCheckedFunction(String string) throws CheckedException {
    if (string.isEmpty()) {
      throw CheckedExceptions.withMessage("Empty string");
    }
    return string.charAt(0);
  }

  static void throwingCheckedConsumer(String string) throws CheckedException {
    if (string.isEmpty()) {
      throw CheckedExceptions.withMessage("Empty string");
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
