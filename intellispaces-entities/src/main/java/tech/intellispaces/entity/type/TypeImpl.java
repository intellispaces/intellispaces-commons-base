package tech.intellispaces.entity.type;

import java.util.List;

class TypeImpl<T, B> extends AbstractType<T> {
  private final Class<B> baseClass;
  private final List<Type<?>> qualifierTypes;

  TypeImpl(Class<B> baseClass) {
    this.baseClass = baseClass;
    this.qualifierTypes = List.of();
  }

  TypeImpl(Class<B> baseClass, List<Type<?>> qualifierTypes) {
    this.baseClass = baseClass;
    this.qualifierTypes = qualifierTypes;
  }

  @Override
  public Class<?> baseClass() {
    return baseClass;
  }

  @Override
  public List<Type<?>> qualifierTypes() {
    return qualifierTypes;
  }
}
