package intellispaces.common.base.function;

@FunctionalInterface
public interface ThrowableBiConsumer<T1, T2, E extends Throwable> {

  void accept(T1 t, T2 t2) throws E;
}
