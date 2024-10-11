package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndLongToObjectFunction<T, R> {

  R apply(T arg1, long arg2);
}
