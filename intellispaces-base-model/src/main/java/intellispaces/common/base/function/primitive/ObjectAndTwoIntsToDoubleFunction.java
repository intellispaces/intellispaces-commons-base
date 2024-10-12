package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoIntsToDoubleFunction<T> {

  double apply(T arg1, int arg2, int arg3);
}
