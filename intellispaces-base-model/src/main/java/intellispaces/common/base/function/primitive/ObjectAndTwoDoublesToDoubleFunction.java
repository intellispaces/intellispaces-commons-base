package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoDoublesToDoubleFunction<T> {

  double apply(T arg1, double arg2, double arg3);
}
