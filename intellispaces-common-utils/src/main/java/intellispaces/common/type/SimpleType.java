package intellispaces.common.type;

class SimpleType<T> implements Type<T> {
  private final Class<T> aClass;

  SimpleType(Class<T> aClass) {
    this.aClass = aClass;
  }

  @Override
  public Class<T> baseClass() {
    return aClass;
  }
}
