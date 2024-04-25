package intellispaces.commons.datahandle;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Data handle functions.
 */
public interface HandleFunctions {

  /**
   * Processes the value with the given handler.
   *
   * @param value value for processing.
   * @param handler value handler.
   * @param <T> value type.
   */
  static <T> void handle(T value, Consumer<T> handler) {
    handler.accept(value);
  }

  /**
   * Sequentially processes the value with the listed handlers.
   *
   * @param value value for processing.
   * @param handler1 first value handler.
   * @param handler2 second value handler.
   * @param <T> value type.
   */
  static <T> void handle(T value, Consumer<T> handler1, Consumer<T> handler2) {
    handler1.accept(value);
    handler2.accept(value);
  }

  /**
   * Sequentially processes the value with the listed handlers.
   *
   * @param value value for processing.
   * @param handler1 first value handler.
   * @param handler2 second value handler.
   * @param handler3 third value handler.
   * @param <T> value type.
   */
  static <T> void handle(T value, Consumer<T> handler1, Consumer<T> handler2, Consumer<T> handler3) {
    handler1.accept(value);
    handler2.accept(value);
    handler3.accept(value);
  }

  /**
   * Sequentially processes the value with the listed handlers.
   *
   * @param value value for processing.
   * @param handler1 first value handler.
   * @param handler2 second value handler.
   * @param handler3 third value handler.
   * @param handler4 fourth value handler.
   * @param <T> value type.
   */
  static <T> void handle(T value, Consumer<T> handler1, Consumer<T> handler2, Consumer<T> handler3, Consumer<T> handler4) {
    handler1.accept(value);
    handler2.accept(value);
    handler3.accept(value);
    handler4.accept(value);
  }

  /**
   * Sequentially processes the value with the listed handlers.
   *
   * @param value value for processing.
   * @param handler1 first value handler.
   * @param handler2 second value handler.
   * @param handler3 third value handler.
   * @param handler4 fourth value handler.
   * @param handler5 fifth value handler.
   * @param <T> value type.
   */
  static <T> void handle(T value, Consumer<T> handler1, Consumer<T> handler2, Consumer<T> handler3, Consumer<T> handler4, Consumer<T> handler5) {
    handler1.accept(value);
    handler2.accept(value);
    handler3.accept(value);
    handler4.accept(value);
    handler5.accept(value);
  }

  /**
   * Processes two values with the given handler.
   *
   * @param value1 first value for processing.
   * @param value2 second value for processing.
   * @param handler value handler.
   * @param <T> value type.
   */
  static <T> void handleEach(T value1, T value2, Consumer<T> handler) {
    handler.accept(value1);
    handler.accept(value2);
  }

  /**
   * Processes three values with the given handler.
   *
   * @param value1 first value for processing.
   * @param value2 second value for processing.
   * @param value3 third value for processing.
   * @param handler value handler.
   * @param <T> value type.
   */
  static <T> void handleEach(T value1, T value2, T value3, Consumer<T> handler) {
    handler.accept(value1);
    handler.accept(value2);
    handler.accept(value3);
  }

  /**
   * Processes four values with the given handler.
   *
   * @param value1 first value for processing.
   * @param value2 second value for processing.
   * @param value3 third value for processing.
   * @param value4 fourth value for processing.
   * @param handler value handler.
   * @param <T> value type.
   */
  static <T> void handleEach(T value1, T value2, T value3, T value4, Consumer<T> handler) {
    handler.accept(value1);
    handler.accept(value2);
    handler.accept(value3);
    handler.accept(value4);
  }

  /**
   * Processes five values with the given handler.
   *
   * @param value1 first value for processing.
   * @param value2 second value for processing.
   * @param value3 third value for processing.
   * @param value4 fourth value for processing.
   * @param value5 fifth value for processing.
   * @param handler value handler.
   * @param <T> value type.
   */
  static <T> void handleEach(T value1, T value2, T value3, T value4, T value5, Consumer<T> handler) {
    handler.accept(value1);
    handler.accept(value2);
    handler.accept(value3);
    handler.accept(value4);
    handler.accept(value5);
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

  static <T, R> R coalesce(T value, Function<T, R> function1, Function<T, R> function2) {
    return coalesce(value, List.of(function1, function2));
  }

  static <T, R> R coalesce(T value, List<Function<T, R>> functions) {
    for (Function<T, R> function : functions) {
      R result = function.apply(value);
      if (result != null) {
        return result;
      }
    }
    return null;
  }
}
