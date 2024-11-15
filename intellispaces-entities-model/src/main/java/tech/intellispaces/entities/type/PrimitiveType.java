package tech.intellispaces.entities.type;

/**
 * Primitive type.
 */
public interface PrimitiveType {

  boolean isChar();

  boolean isBoolean();

  boolean isByte();

  boolean isShort();

  boolean isInt();

  boolean isLong();

  boolean isFloat();

  boolean isDouble();

  String typename();

  Class<?> wrapperClass();
}
