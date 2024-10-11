package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface TwoObjectsAndDoubleToDoubleFunction<T1, T2> {

  double apply(T1 arg1, T2 arg2, double arg3);
}
