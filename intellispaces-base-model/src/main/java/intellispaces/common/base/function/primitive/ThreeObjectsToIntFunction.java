package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ThreeObjectsToIntFunction<T1, T2, T3> {

  int apply(T1 arg1, T2 arg2, T3 arg3);
}
