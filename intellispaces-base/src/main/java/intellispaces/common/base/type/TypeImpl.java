package intellispaces.common.base.type;

import java.util.List;

class TypeImpl<T> implements Type<T> {
  private final Class<T> aClass;
  private final List<Type<?>> qualifiers;

  TypeImpl(Class<T> aClass) {
    this.aClass = aClass;
    this.qualifiers = List.of();
  }

  TypeImpl(Class<T> aClass, List<Type<?>> qualifiers) {
    this.aClass = aClass;
    this.qualifiers = qualifiers;
  }

  @Override
  public Class<T> baseClass() {
    return aClass;
  }

  @Override
  public List<Type<?>> qualifierTypes() {
    return qualifiers;
  }
}
