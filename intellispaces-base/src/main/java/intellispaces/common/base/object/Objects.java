package intellispaces.common.base.object;

/**
 * Object provider.
 */
public interface Objects {

  static <T> T get(Class<T> aClass) {
    return ObjectFunctions.newInstance(aClass);
  }
}
