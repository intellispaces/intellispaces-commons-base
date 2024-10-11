package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoDoublesToObjectFunction<T, R> {

  R apply(T arg1, double arg2, double arg3);
}
