package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndIntToObjectFunction<T, R> {

  R apply(T arg1, int arg2);
}
