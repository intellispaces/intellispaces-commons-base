package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndLongAndDoubleToLongFunction<T> {

  long apply(T arg1, long arg2, double arg3);
}
