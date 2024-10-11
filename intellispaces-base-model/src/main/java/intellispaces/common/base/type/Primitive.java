package intellispaces.common.base.type;

public interface Primitive {

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
