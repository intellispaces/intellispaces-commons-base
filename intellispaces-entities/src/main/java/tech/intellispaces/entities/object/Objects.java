package tech.intellispaces.entities.object;

/**
 * Object provider.
 */
public interface Objects {

  static <T> T get(Class<T> aClass) {
    return ObjectFunctions.newInstance(aClass);
  }

  /**
   * Returns <code>null</code> as {@link Void}.
   */
  static Void getVoid() {
    return null;
  }
}
