package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndIntToIntFunction<T> {

  int apply(T arg1, int arg2);
}
