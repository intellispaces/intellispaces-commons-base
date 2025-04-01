package tech.intellispaces.commons.function;

/**
 * Consumer that accepts three arguments.
 *
 * @param <T1> a first argument type.
 * @param <T2> a second argument type.
 * @param <T3> a third argument type.
 */
@FunctionalInterface
public interface Consumer3<T1, T2, T3> {

  /**
   * Applies function.
   *
   * @param arg1 a first argument.
   * @param arg2 a second argument.
   * @param arg3 a third argument.
   */
  void accept(T1 arg1, T2 arg2, T3 arg3);
}
