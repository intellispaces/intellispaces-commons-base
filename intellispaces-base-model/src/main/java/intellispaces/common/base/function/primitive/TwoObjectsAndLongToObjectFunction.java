package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface TwoObjectsAndLongToObjectFunction<T1, T2, R> {

  R apply(T1 arg1, T2 arg2, long arg3);
}
