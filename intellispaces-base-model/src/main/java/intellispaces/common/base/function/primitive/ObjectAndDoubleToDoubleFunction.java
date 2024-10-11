package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndDoubleToDoubleFunction<T> {

  double apply(T arg1, double arg2);
}
