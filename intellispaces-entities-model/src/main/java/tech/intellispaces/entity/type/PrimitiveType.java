package tech.intellispaces.entity.type;

/**
 * Primitive type.
 */
@SuppressWarnings("rawtypes")
public interface PrimitiveType extends Type {

  /**
   * The primitive typename.
   */
  String typename();

  /**
   * The wrapper class.
   */
  Class<?> wrapperClass();

  boolean is(String name);

  boolean isChar();

  boolean isBoolean();

  boolean isByte();

  boolean isShort();

  boolean isInt();

  boolean isLong();

  boolean isFloat();

  boolean isDouble();
}
