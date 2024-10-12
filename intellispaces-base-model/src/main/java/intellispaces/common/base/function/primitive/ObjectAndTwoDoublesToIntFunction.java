package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoDoublesToIntFunction<T> {

  int apply(T arg1, double arg2, double arg3);
}
