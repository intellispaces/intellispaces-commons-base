package tech.intellispaces.commons.basic.type;

import java.util.List;

class ClassTypeImpl<T, B> extends AbstractClassType<T> {
  private final Class<B> baseClass;
  private final List<Type<?>> qualifierTypes;

  ClassTypeImpl(Class<B> baseClass) {
    this.baseClass = baseClass;
    this.qualifierTypes = List.of();
  }

  ClassTypeImpl(Class<B> baseClass, List<Type<?>> qualifierTypes) {
    this.baseClass = baseClass;
    this.qualifierTypes = qualifierTypes;
  }

  @Override
  public Class<?> baseClass() {
    return baseClass;
  }

  @Override
  public Type<?> baseType() {
    return Types.get(baseClass);
  }

  @Override
  public List<Type<?>> qualifierTypes() {
    return qualifierTypes;
  }
}
