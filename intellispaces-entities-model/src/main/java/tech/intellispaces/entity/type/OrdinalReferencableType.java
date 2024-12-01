package tech.intellispaces.entity.type;

public interface OrdinalReferencableType<T> extends Type<T> {

  int ordinal();

  boolean is(int ordinal);
}
