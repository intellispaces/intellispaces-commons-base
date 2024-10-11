package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndDoubleToObjectFunction<T, R> {

  R apply(T arg1, double arg2);
}
