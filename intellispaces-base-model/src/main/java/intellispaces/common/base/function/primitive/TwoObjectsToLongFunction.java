package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface TwoObjectsToLongFunction<T1, T2> {

  long apply(T1 arg1, T2 arg2);
}
