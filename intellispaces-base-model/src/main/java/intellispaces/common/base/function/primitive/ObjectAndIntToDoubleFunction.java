package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndIntToDoubleFunction<T> {

  double apply(T arg1, int arg2);
}
