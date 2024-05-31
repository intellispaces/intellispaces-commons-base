package tech.intellispaces.framework.commons.action;

import tech.intellispaces.framework.commons.function.QuadFunction;
import tech.intellispaces.framework.commons.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Action builders.
 */
public interface ActionBuilders {

  static Action action(Runnable runnable) {
    return new RunnableBaseAction(runnable);
  }

  static <T> Action action(Consumer<T> consumer, T value) {
    return action(() -> consumer.accept(value));
  }

  static <R> ResettableGetter<R> resettableGetter() {
    return new ResettableGetter<>();
  }

  static <R> ResettableGetter<R> resettableGetter(R initValue) {
    return new ResettableGetter<>(initValue);
  }

  /**
   * Builds getter action on base given supplier.
   *
   * @param supplier value supplier
   * @param <R> getter result value type.
   * @return getter action.
   */
  static <R> CachedLazyGetter<R> cachedLazyGetter(Supplier<R> supplier) {
    return new CachedLazyGetter<>(supplier);
  }

  /**
   * Builds getter action on base given value and calculating function.
   *
   * @param function calculating function.
   * @param arg function argument.
   * @param <R> getter result value type.
   * @param <T> type of the function argument.
   * @return getter action.
   */
  static <R, T> CachedLazyGetter<R> cachedLazyGetter(Function<T, R> function, T arg) {
    return cachedLazyGetter(() -> function.apply(arg));
  }

  /**
   * Builds getter action on base given two values and calculating function.
   *
   * @param function calculating function.
   * @param arg1 first function argument.
   * @param arg2 second function argument.
   * @param <R> getter result value type.
   * @param <T1> type of the first function argument.
   * @param <T2> type of the second function argument.
   * @return getter action.
   */
  static <R, T1, T2> CachedLazyGetter<R> cachedLazyGetter(BiFunction<T1, T2, R> function, T1 arg1, T2 arg2) {
    return cachedLazyGetter(() -> function.apply(arg1, arg2));
  }

  /**
   * Builds getter action on base given three values and calculating function.
   *
   * @param function calculating function.
   * @param arg1 first function argument.
   * @param arg2 second function argument.
   * @param arg3 third function argument.
   * @param <R> getter result value type.
   * @param <T1> type of the first function argument.
   * @param <T2> type of the second function argument.
   * @param <T3> type of the third function argument.
   * @return getter action.
   */
  static <R, T1, T2, T3> CachedLazyGetter<R> cachedLazyGetter(TriFunction<T1, T2, T3, R> function, T1 arg1, T2 arg2, T3 arg3) {
    return cachedLazyGetter(() -> function.apply(arg1, arg2, arg3));
  }

  /**
   * Builds getter action on base given four values and calculating function.
   *
   * @param function calculating function.
   * @param arg1 first function argument.
   * @param arg2 second function argument.
   * @param arg3 third function argument.
   * @param arg4 fourth source value.
   * @param <R> getter result value type.
   * @param <T1> type of the first function argument.
   * @param <T2> type of the second function argument.
   * @param <T3> type of the third function argument.
   * @param <T4> type of the fourth function argument.
   * @return getter action.
   */
  static <R, T1, T2, T3, T4> CachedLazyGetter<R> cachedLazyGetter(QuadFunction<T1, T2, T3, T4, R> function, T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
    return cachedLazyGetter(() -> function.apply(arg1, arg2, arg3, arg4));
  }
}
