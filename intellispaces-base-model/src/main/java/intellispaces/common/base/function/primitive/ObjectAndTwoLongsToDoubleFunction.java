package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoLongsToDoubleFunction<T> {

  double apply(T arg1, long arg2, long arg3);
}
