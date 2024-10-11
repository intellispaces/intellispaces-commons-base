package intellispaces.common.base.type;

public enum Primitives {

  Boolean("boolean"),

  Char("char"),

  Byte("byte"),

  Short("short"),

  Long("long"),

  Int("int"),

  Float("float"),

  Double("double");

  private final String typename;

  Primitives(String typename) {
    this.typename = typename;
  }

  public String typename() {
    return typename;
  }
}
