package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndLongAndDoubleToObjectFunction<T, R> {

  R apply(T arg1, long arg2, double arg3);
}
