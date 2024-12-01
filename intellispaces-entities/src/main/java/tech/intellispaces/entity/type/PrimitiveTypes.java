package tech.intellispaces.entity.type;

import tech.intellispaces.entity.exception.UnexpectedExceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Primitive type provider.
 */
public enum PrimitiveTypes implements PrimitiveType {

  Boolean("boolean", boolean.class, java.lang.Boolean.class),

  Char("char", char.class, java.lang.Character.class),

  Byte("byte", byte.class, java.lang.Byte.class),

  Short("short", short.class, java.lang.Short.class),

  Int("int", int.class, java.lang.Integer.class),

  Long("long", long.class, java.lang.Long.class),

  Float("float", float.class, java.lang.Float.class),

  Double("double", double.class, java.lang.Double.class);

  private final String typename;
  private final Class<?> baseClass;
  private final Class<?> wrapperClass;

  public static PrimitiveType get(String typename) {
    PrimitiveType primitiveType = VALUES.get(typename);
    if (primitiveType == null) {
      throw UnexpectedExceptions.withMessage("Not primitive typename: {0}", typename);
    }
    return primitiveType;
  }

  PrimitiveTypes(String typename, Class<?> baseClass, Class<?> wrapperClass) {
    this.typename = typename;
    this.baseClass = baseClass;
    this.wrapperClass = wrapperClass;
  }

  @Override
  public String typename() {
    return typename;
  }

  @Override
  public Class<?> wrapperClass() {
    return wrapperClass;
  }

  @Override
  public Class<?> baseClass() {
    return baseClass;
  }

  @Override
  public List<Type<?>> qualifierTypes() {
    return List.of();
  }

  @Override
  public boolean is(String name) {
    return this.name().equals(name);
  }

  @Override
  public boolean is(int ordinal) {
    return this.ordinal() == ordinal;
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
