package tech.intellispaces.commons.basic.entity;

/**
 * The enumeration.
 *
 * @param <T> the enumerated type.
 */
public interface Enumeration<T> extends Enumerable<T> {

  @Override
  default boolean is(String name) {
    return this.name().equals(name);
  }

  @Override
  default boolean is(int ordinal) {
    return this.ordinal() == ordinal;
  }

  default boolean is(Enumerable<T> enumerable) {
    return this.is(enumerable.name());
  }

  default boolean isNot(Enumerable<T> enumerable) {
    return !is(enumerable);
  }
}
