package tech.intellispaces.general.function;

/**
 * Consumer that accepts three arguments.
 *
 * @param <T1> the first argument type.
 * @param <T2> the second argument type.
 * @param <T3> the third argument type.
 */
@FunctionalInterface
public interface TriConsumer<T1, T2, T3> {

  /**
   * Applies function.
   *
   * @param arg1 the first argument.
   * @param arg2 the second argument.
   * @param arg3 the third argument.
   */
  void accept(T1 arg1, T2 arg2, T3 arg3);
}
