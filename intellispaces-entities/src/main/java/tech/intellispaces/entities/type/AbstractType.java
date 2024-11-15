package tech.intellispaces.entities.type;

import java.util.Objects;

public abstract class AbstractType<T> implements Type<T> {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !Type.class.isAssignableFrom(o.getClass())) {
      return false;
    }
    Type<?> type = (Type<?>) o;
    return Objects.equals(baseClass(), type.baseClass())
        && Objects.equals(qualifierTypes(), type.qualifierTypes());
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseClass(), qualifierTypes());
  }
}
