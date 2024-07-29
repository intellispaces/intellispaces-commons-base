package tech.intellispaces.commons.function;

import tech.intellispaces.commons.exception.CoveredCheckedException;

public interface RunnableFunctions {

  @SuppressWarnings("unchecked")
  static <E extends Throwable> void runAndUncoverIfCovered(Runnable runnable, Class<E> e) throws E {
    try {
      runnable.run();
    } catch (CoveredCheckedException se) {
      if (e.isInstance(se.getCause())) {
        throw (E) se.getCause();
      } else {
        throw se;
      }
    }
  }
}
