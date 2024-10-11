package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndLongToDoubleFunction<T> {

  double apply(T arg1, long arg2);
}
