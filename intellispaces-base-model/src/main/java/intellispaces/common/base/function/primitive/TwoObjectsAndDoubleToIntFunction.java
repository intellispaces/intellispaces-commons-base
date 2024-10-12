package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface TwoObjectsAndDoubleToIntFunction<T1, T2> {

  int apply(T1 arg1, T2 arg2, double arg3);
}
