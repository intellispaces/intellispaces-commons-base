package tech.intellispaces.framework.commons.function;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Throwable> {

  void accept(T t) throws E;
}
