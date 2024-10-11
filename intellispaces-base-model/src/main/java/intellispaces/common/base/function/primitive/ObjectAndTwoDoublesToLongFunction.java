package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoDoublesToLongFunction<T> {

  long apply(T arg1, double arg2, double arg3);
}
