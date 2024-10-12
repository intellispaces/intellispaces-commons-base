package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface TwoObjectsAndIntToIntFunction<T1, T2> {

  int apply(T1 arg1, T2 arg2, int arg3);
}
