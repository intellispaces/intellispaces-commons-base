package tech.intellispaces.commons.base.type;

import tech.intellispaces.commons.base.entity.Enumerable;

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
