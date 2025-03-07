package tech.intellispaces.commons.object;

import tech.intellispaces.commons.exception.UnexpectedExceptions;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Object related functions.
 */
public interface ObjectFunctions {

  static <T> int castToInt(T object) {
    if (object instanceof Long || object instanceof Float || object instanceof Double) {
      throw UnexpectedExceptions.withMessage("Unsupported operation");
    } else if (object instanceof Number) {
      return ((Number) object).intValue();
    } else if (object instanceof Character) {
      return (char) object;
    }
    throw UnexpectedExceptions.withMessage("Unsupported operation");
  }

  static <T> double castToDouble(T object) {
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

  /**
   * Processes the object with the given handler.
   *
   * @param object object for processing.
   * @param handler object handler.
   * @param <T> object type.
   */
  static <T> void handle(T object, Consumer<T> handler) {
    handler.accept(object);
  }

  /**
   * Sequentially processes the object with the listed handlers.
   *
   * @param object object for processing.
   * @param handler1 first object handler.
   * @param handler2 second object handler.
   * @param <T> object type.
   */
  static <T> void handle(
      T object,
      Consumer<T> handler1,
      Consumer<T> handler2
  ) {
    handler1.accept(object);
    handler2.accept(object);
  }

  /**
   * Sequentially processes the object with the listed handlers.
   *
   * @param object object for processing.
   * @param handler1 first object handler.
   * @param handler2 second object handler.
   * @param handler3 third object handler.
   * @param <T> object type.
   */
  static <T> void handle(
      T object,
      Consumer<T> handler1,
      Consumer<T> handler2,
      Consumer<T> handler3
  ) {
    handler1.accept(object);
    handler2.accept(object);
    handler3.accept(object);
  }

  /**
   * Sequentially processes the object with the listed handlers.
   *
   * @param object object for processing.
   * @param handler1 first object handler.
   * @param handler2 second object handler.
   * @param handler3 third object handler.
   * @param handler4 fourth object handler.
   * @param <T> object type.
   */
  static <T> void handle(
      T object,
      Consumer<T> handler1,
      Consumer<T> handler2,
      Consumer<T> handler3,
      Consumer<T> handler4
  ) {
    handler1.accept(object);
    handler2.accept(object);
    handler3.accept(object);
    handler4.accept(object);
  }

  /**
   * Sequentially processes the object with the listed handlers.
   *
   * @param object object for processing.
   * @param handler1 first object handler.
   * @param handler2 second object handler.
   * @param handler3 third object handler.
   * @param handler4 fourth object handler.
   * @param handler5 fifth object handler.
   * @param <T> object type.
   */
  static <T> void handle(
      T object,
      Consumer<T> handler1,
      Consumer<T> handler2,
      Consumer<T> handler3,
      Consumer<T> handler4,
      Consumer<T> handler5
  ) {
    handler1.accept(object);
    handler2.accept(object);
    handler3.accept(object);
    handler4.accept(object);
    handler5.accept(object);
  }

  /**
   * Processes two values with the given handler.
   *
   * @param object1 first object for processing.
   * @param object2 second object for processing.
   * @param handler object handler.
   * @param <T> object type.
   */
  static <T> void handleEach(
      T object1,
      T object2,
      Consumer<T> handler
  ) {
    handler.accept(object1);
    handler.accept(object2);
  }

  /**
   * Processes three values with the given handler.
   *
   * @param object1 first object for processing.
   * @param object2 second object for processing.
   * @param object3 third object for processing.
   * @param handler object handler.
   * @param <T> object type.
   */
  static <T> void handleEach(
      T object1,
      T object2,
      T object3,
      Consumer<T> handler
  ) {
    handler.accept(object1);
    handler.accept(object2);
    handler.accept(object3);
  }

  /**
   * Processes four values with the given handler.
   *
   * @param object1 first object for processing.
   * @param object2 second object for processing.
   * @param object3 third object for processing.
   * @param object4 fourth object for processing.
   * @param handler object handler.
   * @param <T> object type.
   */
  static <T> void handleEach(
      T object1,
      T object2,
      T object3,
      T object4,
      Consumer<T> handler
  ) {
    handler.accept(object1);
    handler.accept(object2);
    handler.accept(object3);
    handler.accept(object4);
  }

  /**
   * Processes five values with the given handler.
   *
   * @param object1 first object for processing.
   * @param object2 second object for processing.
   * @param object3 third object for processing.
   * @param object4 fourth object for processing.
   * @param object5 fifth object for processing.
   * @param handler object handler.
   * @param <T> object type.
   */
  static <T> void handleEach(
      T object1,
      T object2,
      T object3,
      T object4,
      T object5,
      Consumer<T> handler
  ) {
    handler.accept(object1);
    handler.accept(object2);
    handler.accept(object3);
    handler.accept(object4);
    handler.accept(object5);
  }

  static <T> T coalesce(Supplier<T> suppler1, Supplier<T> suppler2) {
    return coalesce(List.of(suppler1, suppler2));
  }

  static <T> T coalesce(List<Supplier<T>> suppliers) {
    for (Supplier<T> supplier : suppliers) {
      T result = supplier.get();
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  static <T, R> R coalesce(T object, Function<T, R> function1, Function<T, R> function2) {
    return coalesce(object, List.of(function1, function2));
  }

  static <T, R> R coalesce(T object, List<Function<T, R>> functions) {
    for (Function<T, R> function : functions) {
      R result = function.apply(object);
      if (result != null) {
        return result;
      }
    }
    return null;
  }
}
