package tech.intellispaces.entity.type;

/**
 * The class-based type.
 *
 * @param <T> associated type.
 */
public interface ClassType<T> extends Type<T> {

  /**
   * The base class.
   */
  Class<?> baseClass();
}
