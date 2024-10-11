package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndLongAndDoubleToDoubleFunction<T> {

  double apply(T arg1, long arg2, double arg3);
}
