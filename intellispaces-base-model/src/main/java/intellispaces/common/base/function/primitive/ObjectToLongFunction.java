package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectToLongFunction<T> {

  long apply(T arg);
}
