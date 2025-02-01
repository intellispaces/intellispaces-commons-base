package tech.intellispaces.commons.basic.type;

import tech.intellispaces.commons.basic.entity.Enumerable;

/**
 * The primitive type.
 */
@SuppressWarnings("rawtypes")
public interface PrimitiveType extends Type, Enumerable<PrimitiveType> {

  /**
   * The primitive typename.
   */
  String typename();

  /**
   * The wrapper class.
   */
  Class<?> wrapperClass();

  boolean isChar();

  boolean isBoolean();

  boolean isByte();

  boolean isShort();

  boolean isInt();

  boolean isLong();

  boolean isFloat();

  boolean isDouble();
}
