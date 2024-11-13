package tech.intellispaces.common.entities.type;

import tech.intellispaces.common.entities.exception.UnexpectedExceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Primitive type provider.
 */
public enum PrimitiveTypes implements PrimitiveType {

  Boolean("boolean", java.lang.Boolean.class),

  Char("char", java.lang.Character.class),

  Byte("byte", java.lang.Byte.class),

  Short("short", java.lang.Short.class),

  Int("int", java.lang.Integer.class),

  Long("long", java.lang.Long.class),

  Float("float", java.lang.Float.class),

  Double("double", java.lang.Double.class);

  private final String typename;
  private final Class<?> wrapperClass;

  public static PrimitiveType get(String typename) {
    PrimitiveType primitiveType = VALUES.get(typename);
    if (primitiveType == null) {
      throw UnexpectedExceptions.withMessage("Not primitive typename: {0}", typename);
    }
    return primitiveType;
  }

  PrimitiveTypes(String typename, Class<?> wrapperClass) {
    this.typename = typename;
    this.wrapperClass = wrapperClass;
  }

  @Override
  public boolean isChar() {
    return this == PrimitiveTypes.Char;
  }

  @Override
  public boolean isBoolean() {
    return this == PrimitiveTypes.Boolean;
  }

  @Override
  public boolean isByte() {
    return this == PrimitiveTypes.Byte;
  }

  @Override
  public boolean isShort() {
    return this == PrimitiveTypes.Short;
  }

  @Override
  public boolean isInt() {
    return this == PrimitiveTypes.Int;
  }

  @Override
  public boolean isLong() {
    return this == PrimitiveTypes.Long;
  }

  @Override
  public boolean isFloat() {
    return this == PrimitiveTypes.Float;
  }

  @Override
  public boolean isDouble() {
    return this == PrimitiveTypes.Double;
  }

  @Override
  public String typename() {
    return typename;
  }

  @Override
  public Class<?> wrapperClass() {
    return wrapperClass;
  }

  private static final Map<String, PrimitiveType> VALUES = new HashMap<>();
  static {
    VALUES.put("boolean", Boolean);
    VALUES.put("char", Char);
    VALUES.put("byte", Byte);
    VALUES.put("short", Short);
    VALUES.put("long", Long);
    VALUES.put("int", Int);
    VALUES.put("float", Float);
    VALUES.put("double", Double);
  }
}
