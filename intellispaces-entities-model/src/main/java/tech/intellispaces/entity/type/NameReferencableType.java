package tech.intellispaces.entity.type;

public interface NameReferencableType<T> extends Type<T> {

  String name();

  boolean is(String name);
}
