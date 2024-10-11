package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ThreeObjectsToDoubleFunction<T1, T2, T3> {

  double apply(T1 arg1, T2 arg2, T3 arg3);
}
