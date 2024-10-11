package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectToDoubleFunction<T> {

  double apply(T arg);
}
