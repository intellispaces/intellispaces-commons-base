package intellispaces.common.base.function.primitive;

@FunctionalInterface
public interface ObjectAndTwoIntsToObjectFunction<T, R> {

  R apply(T arg1, int arg2, int arg3);
}
