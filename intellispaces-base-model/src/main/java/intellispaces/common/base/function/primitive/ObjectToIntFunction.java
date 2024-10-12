package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectToIntFunction<T> {

  int apply(T arg);
}
