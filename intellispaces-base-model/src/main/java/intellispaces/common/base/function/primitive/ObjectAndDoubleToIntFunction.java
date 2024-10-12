package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndDoubleToIntFunction<T> {

  int apply(T arg1, double arg2);
}
