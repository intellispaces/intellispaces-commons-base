package intellispaces.commons;

import intellispaces.commons.action.CachedLazyGetterAction;
import intellispaces.commons.action.GetterAction;
import intellispaces.commons.function.QuadFunction;
import intellispaces.commons.function.TriFunction;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ActionFunctions {

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

  /**
   * Returns getter action on base given supplier.
   *
   * @param supplier value supplier
   * @param <T> getter result value type.
   * @return getter action.
   */
  static <T> GetterAction<T> cachedLazyGetter(Supplier<T> supplier) {
    return new CachedLazyGetterAction<>(supplier);
  }

  /**
   * Returns getter action on base given value and calculating function.
   *
   * @param function calculating function.
   * @param value source value.
   * @param <T> getter result value type.
   * @param <V> type of the source value.
   * @return getter action.
   */
  static <T, V> GetterAction<T> cachedLazyGetter(Function<V, T> function, V value) {
    return cachedLazyGetter(() -> function.apply(value));
  }

  /**
   * Returns getter action on base given two values and calculating function.
   *
   * @param function calculating function.
   * @param value1 first source value.
   * @param value2 second source value.
   * @param <T> getter result value type.
   * @param <V1> type of the first source value.
   * @param <V2> type of the second source value.
   * @return getter action.
   */
  static <T, V1, V2> GetterAction<T> cachedLazyGetter(BiFunction<V1, V2, T> function, V1 value1, V2 value2) {
    return cachedLazyGetter(() -> function.apply(value1, value2));
  }

  /**
   * Returns getter action on base given three values and calculating function.
   *
   * @param function calculating function.
   * @param value1 first source value.
   * @param value2 second source value.
   * @param value3 third source value.
   * @param <T> getter result value type.
   * @param <V1> type of the first source value.
   * @param <V2> type of the second source value.
   * @param <V3> type of the third source value.
   * @return getter action.
   */
  static <T, V1, V2, V3> GetterAction<T> cachedLazyGetter(TriFunction<V1, V2, V3, T> function, V1 value1, V2 value2, V3 value3) {
    return cachedLazyGetter(() -> function.apply(value1, value2, value3));
  }

  /**
   * Returns getter action on base given four values and calculating function.
   *
   * @param function calculating function.
   * @param value1 first source value.
   * @param value2 second source value.
   * @param value3 third source value.
   * @param value4 fourth source value.
   * @param <T> getter result value type.
   * @param <V1> type of the first source value.
   * @param <V2> type of the second source value.
   * @param <V3> type of the third source value.
   * @param <V4> type of the fourth source value.
   * @return getter action.
   */
  static <T, V1, V2, V3, V4> GetterAction<T> cachedLazyGetter(QuadFunction<V1, V2, V3, V4, T> function, V1 value1, V2 value2, V3 value3, V4 value4) {
    return cachedLazyGetter(() -> function.apply(value1, value2, value3, value4));
  }
}
