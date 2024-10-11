package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface TwoObjectsAndDoubleToObjectFunction<T1, T2, R> {

  R apply(T1 arg1, T2 arg2, double arg3);
}
