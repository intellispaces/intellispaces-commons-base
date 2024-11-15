package tech.intellispaces.entities.type;

import java.util.List;

class TypeImpl<T> extends AbstractType<T> {
  private final Class<?> baseClass;
  private final List<Type<?>> qualifierTypes;

  TypeImpl(Class<T> baseClass) {
    this.baseClass = baseClass;
    this.qualifierTypes = List.of();
  }

  TypeImpl(Class<?> baseClass, List<Type<?>> qualifierTypes) {
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
