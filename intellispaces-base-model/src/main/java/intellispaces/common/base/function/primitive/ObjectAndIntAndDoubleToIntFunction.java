package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndIntAndDoubleToIntFunction<T> {

  int apply(T arg1, int arg2, double arg3);
}
