package intellispaces.common.base.object;

import intellispaces.common.base.exception.UnexpectedExceptions;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * Common object related functions.
 */
public interface ObjectFunctions {

  static <T> T newInstance(Class<T> aClass) {
    Objects.requireNonNull(aClass);
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

  static <T> int convertToInt(T object) {
    if (object instanceof Long || object instanceof Float || object instanceof Double) {
      throw UnexpectedExceptions.withMessage("Unsupported operation");
    } else if (object instanceof Number) {
      return ((Number) object).intValue();
    } else if (object instanceof Character) {
      return (char) object;
    }
    throw UnexpectedExceptions.withMessage("Unsupported operation");
  }

  static <T> double convertToDouble(T object) {
    if (object instanceof Number) {
      return ((Number) object).doubleValue();
    } else if (object instanceof Character) {
      return (char) object;
    }
    throw UnexpectedExceptions.withMessage("Unsupported operation");
  }

  static <E> boolean equalsAnyOf(E object, E object1, E object2) {
    if (Objects.equals(object, object1) || Objects.equals(object, object2)) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E object, E object1, E object2, E object3) {
    if (Objects.equals(object, object1)
        || Objects.equals(object, object2)
        || Objects.equals(object, object3)
    ) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E object, E object1, E object2, E object3, E object4) {
    if (Objects.equals(object, object1)
        || Objects.equals(object, object2)
        || Objects.equals(object, object3)
        || Objects.equals(object, object4)
    ) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E object, E object1, E object2, E object3, E object4, E object5) {
    if (Objects.equals(object, object1)
        || Objects.equals(object, object2)
        || Objects.equals(object, object3)
        || Objects.equals(object, object4)
        || Objects.equals(object, object5)
    ) {
      return true;
    }
    return false;
  }

  static <E> boolean equalsAnyOf(E object, E... objects) {
    for (E e : objects) {
      if (Objects.equals(object, e)) {
        return true;
      }
    }
    return false;
  }
}
