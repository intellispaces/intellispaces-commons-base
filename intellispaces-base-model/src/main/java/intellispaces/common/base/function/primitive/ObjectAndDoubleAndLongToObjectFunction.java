package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndDoubleAndLongToObjectFunction<T, R> {

  R apply(T arg1, double arg2, long arg3);
}
