package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface TwoObjectsAndLongToLongFunction<T1, T2> {

  long apply(T1 arg1, T2 arg2, long arg3);
}
