package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoLongsToObjectFunction<T, R> {

  R apply(T arg1, long arg2, long arg3);
}
