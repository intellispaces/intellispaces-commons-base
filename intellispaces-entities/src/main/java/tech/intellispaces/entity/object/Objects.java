package tech.intellispaces.entity.object;

import tech.intellispaces.entity.exception.UnexpectedExceptions;

import java.lang.reflect.Constructor;

/**
 * Object provider.
 */
public interface Objects {

  static <T> T get(Class<T> aClass) {
    java.util.Objects.requireNonNull(aClass);
    try {
      Constructor<T> constructor = aClass.getConstructor();
      return constructor.newInstance();
    } catch (NoSuchMethodException e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Class {0} does not contain default constructor " +
          "without parameters", aClass.getCanonicalName());
    } catch (Exception e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Failed to create instance of the class {0}",
          aClass.getCanonicalName());
    }
  }

  /**
   * Returns <code>null</code> as {@link Void}.
   */
  static Void getVoid() {
    return null;
  }
}
