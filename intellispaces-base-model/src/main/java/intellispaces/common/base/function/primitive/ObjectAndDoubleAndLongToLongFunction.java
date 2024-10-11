package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndDoubleAndLongToLongFunction<T> {

  long apply(T arg1, double arg2, long arg3);
}
