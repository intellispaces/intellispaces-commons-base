package intellispaces.common.base.type;

import java.util.List;

public interface Types {

  static <T> Type<T> of(Class<T> aClass) {
    return new TypeImpl<>(aClass);
  }

  static <T, Q> Type<T> of(Class<T> aClass, Class<Q> qualifierClass) {
    return new TypeImpl<>(aClass, List.of(Types.of(qualifierClass)));
  }

  static <T, Q1, Q2> Type<T> of(Class<T> aClass, Class<Q1> qualifierClass1, Class<Q2> qualifierClass2) {
    return new TypeImpl<>(aClass, List.of(Types.of(qualifierClass1), Types.of(qualifierClass2)));
  }

  static <T, Q1, Q2, Q3> Type<T> of(Class<T> aClass, Class<Q1> qualifierClass1, Class<Q2> qualifierClass2, Class<Q3> qualifierClass3) {
    return new TypeImpl<>(aClass, List.of(Types.of(qualifierClass1), Types.of(qualifierClass2), Types.of(qualifierClass3)));
  }
}
