package intellispaces.common.base.object;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Functions related to object processing.
 */
public interface ProcessingFunctions {

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
