package tech.intellispaces.framework.commons.action;

import tech.intellispaces.framework.commons.action.getter.ResettableGetter;
import tech.intellispaces.framework.commons.action.getter.SupplierBasedGetter;
import tech.intellispaces.framework.commons.action.handler.ConsumerBasedHandler;
import tech.intellispaces.framework.commons.action.onetime.CachedFirstTimeOnlyAction;
import tech.intellispaces.framework.commons.action.onetime.FirstTimeOnlyAction;
import tech.intellispaces.framework.commons.action.onetime.NotFirstTimeOnlyAction;
import tech.intellispaces.framework.commons.action.executor.RunnableBasedExecutor;
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

  static Executor runner(Runnable runnable) {
    return new RunnableBasedExecutor(runnable);
  }

  static <D> Executor runner(Consumer<D> consumer, D value) {
    return runner(() -> consumer.accept(value));
  }

  static <V> Getter<V> getter(V value) {
    return new ResettableGetter<>(value);
  }

  static <V> ResettableGetter<V> resettableGetter() {
    return new ResettableGetter<>();
  }

  static <V> ResettableGetter<V> resettableGetter(V initValue) {
    return new ResettableGetter<>(initValue);
  }

  /**
   * Builds getter action on base given supplier.
   *
   * @param supplier value supplier
   * @param <V> getter result value type.
   * @return getter action.
   */
  static <V> Getter<V> cachedLazyGetter(Supplier<V> supplier) {
    return new SupplierBasedGetter<>(supplier).wrap(cachedFirstTimeOnlyAction());
  }

  /**
   * Builds getter action on base given value and calculating function.
   *
   * @param function calculating function.
   * @param arg function argument.
   * @param <V> getter result value type.
   * @param <D> type of the function argument.
   * @return getter action.
   */
  static <V, D> Getter<V> cachedLazyGetter(Function<D, V> function, D arg) {
    return cachedLazyGetter(() -> function.apply(arg));
  }

  /**
   * Builds getter action on base given two values and calculating function.
   *
   * @param function calculating function.
   * @param arg1 first function argument.
   * @param arg2 second function argument.
   * @param <V> getter result value type.
   * @param <D1> type of the first function argument.
   * @param <D2> type of the second function argument.
   * @return getter action.
   */
  static <V, D1, D2> Getter<V> cachedLazyGetter(
      BiFunction<D1, D2, V> function, D1 arg1, D2 arg2
  ) {
    return cachedLazyGetter(() -> function.apply(arg1, arg2));
  }

  /**
   * Builds getter action on base given three values and calculating function.
   *
   * @param function calculating function.
   * @param arg1 first function argument.
   * @param arg2 second function argument.
   * @param arg3 third function argument.
   * @param <V> getter result value type.
   * @param <D1> type of the first function argument.
   * @param <D2> type of the second function argument.
   * @param <D3> type of the third function argument.
   * @return getter action.
   */
  static <V, D1, D2, D3> Getter<V> cachedLazyGetter(
      TriFunction<D1, D2, D3, V> function, D1 arg1, D2 arg2, D3 arg3
  ) {
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
   * @param <V> getter result value type.
   * @param <D1> type of the first function argument.
   * @param <D2> type of the second function argument.
   * @param <D3> type of the third function argument.
   * @param <D4> type of the fourth function argument.
   * @return getter action.
   */
  static <V, D1, D2, D3, D4> Getter<V> cachedLazyGetter(
      QuadFunction<D1, D2, D3, D4, V> function, D1 arg1, D2 arg2, D3 arg3, D4 arg4
  ) {
    return cachedLazyGetter(() -> function.apply(arg1, arg2, arg3, arg4));
  }

  static <D> Handler<D> handler(Consumer<D> consumer) {
    return new ConsumerBasedHandler<>(consumer);
  }

  static <V, D1, D2, D3, D4, D5> Function<Action<V, D1, D2, D3, D4, D5>, Action<V, D1, D2, D3, D4, D5>> firstTimeOnlyAction() {
    return FirstTimeOnlyAction::new;
  }

  static <V, D1, D2, D3, D4, D5> Function<Action<V, D1, D2, D3, D4, D5>, Action<V, D1, D2, D3, D4, D5>> cachedFirstTimeOnlyAction() {
    return CachedFirstTimeOnlyAction::new;
  }

  static <V, D1, D2, D3, D4, D5> Function<Action<V, D1, D2, D3, D4, D5>, Action<V, D1, D2, D3, D4, D5>> notFirstTimeOnlyAction() {
    return NotFirstTimeOnlyAction::new;
  }
}
