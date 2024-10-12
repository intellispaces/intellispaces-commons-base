package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndIntAndDoubleToDoubleFunction<T> {

  double apply(T arg1, int arg2, double arg3);
}
