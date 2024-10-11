package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndLongToLongFunction<T> {

  long apply(T arg1, long arg2);
}
