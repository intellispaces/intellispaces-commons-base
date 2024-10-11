package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoLongsToLongFunction<T> {

  long apply(T arg1, long arg2, long arg3);
}
