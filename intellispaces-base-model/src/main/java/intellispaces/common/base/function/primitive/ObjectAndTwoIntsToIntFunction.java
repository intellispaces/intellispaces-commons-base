package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoIntsToIntFunction<T> {

  int apply(T arg1, int arg2, int arg3);
}
