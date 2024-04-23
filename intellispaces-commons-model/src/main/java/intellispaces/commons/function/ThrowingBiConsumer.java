package intellispaces.commons.function;

@FunctionalInterface
public interface ThrowingBiConsumer<T1, T2, E extends Exception> {

  void accept(T1 t, T2 t2) throws E;
}
