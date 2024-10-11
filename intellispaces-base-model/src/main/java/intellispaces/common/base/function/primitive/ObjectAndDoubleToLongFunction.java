package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndDoubleToLongFunction<T> {

  long apply(T arg1, double arg2);
}
