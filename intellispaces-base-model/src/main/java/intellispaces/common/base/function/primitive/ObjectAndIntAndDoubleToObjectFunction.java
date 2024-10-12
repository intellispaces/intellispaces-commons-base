package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndIntAndDoubleToObjectFunction<T, R> {

  R apply(T arg1, int arg2, double arg3);
}
