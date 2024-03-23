package intellispaces.commons.function;

import intellispaces.commons.object.action.CachedLazyGetterAction;
import intellispaces.commons.model.action.GetterAction;
import intellispaces.commons.model.function.QuadFunction;
import intellispaces.commons.model.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Action functions.
 */
public interface ActionFunctions {

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
