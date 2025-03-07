package tech.intellispaces.commons.type;

import java.util.Objects;

public abstract class AbstractClassType<T> implements ClassType<T> {

  @Override
  public Type<?> baseType() {
    return Types.get(baseClass());
  }

  @Override
  public ClassType<T> asClassType() {
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !Type.class.isAssignableFrom(o.getClass())) {
      return false;
    }
    var type = (ClassType<?>) o;
    return Objects.equals(baseClass(), type.baseClass())
        && Objects.equals(qualifierTypes(), type.qualifierTypes());
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseClass(), qualifierTypes());
  }
}
