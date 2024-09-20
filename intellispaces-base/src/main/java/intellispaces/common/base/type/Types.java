package intellispaces.common.base.type;

import java.util.List;

public interface Types {

  static <T> Type<T> of(Class<T> aClass) {
    return new TypeImpl<>(aClass);
  }

  static <R extends B, B, Q> Type<R> of(
    Class<B> baseClass,
    Type<Q> qualifierType
  ) {
    return new TypeImpl<>(baseClass, List.of(qualifierType));
  }

  static <R extends B, B, Q1, Q2> Type<R> of(
    Class<B> baseClass,
    Type<Q1> qualifierType1,
    Type<Q2> qualifierType2
  ) {
    return new TypeImpl<>(
      baseClass,
      List.of(qualifierType1, qualifierType2)
    );
  }

  static <R extends B, B, Q1, Q2, Q3> Type<R> of(
    Class<B> baseClass,
    Type<Q1> qualifierType1,
    Type<Q2> qualifierType2,
    Type<Q3> qualifierType3
  ) {
    return new TypeImpl<>(baseClass, List.of(qualifierType1, qualifierType2, qualifierType3));
  }

  static <R extends B, B, Q> Type<R> of(
    Class<B> baseClass, Class<Q> qualifierClass
  ) {
    return of(baseClass, Types.of(qualifierClass));
  }

  static <R extends B, B, Q1, Q2> Type<R> of(
    Class<B> baseClass, Class<Q1> qualifierClass1, Class<Q2> qualifierClass2
  ) {
    return of(baseClass, Types.of(qualifierClass1), Types.of(qualifierClass2));
  }

  static <R extends B, B, Q1, Q2, Q3> Type<R> of(
    Class<B> baseClass,
    Class<Q1> qualifierClass1,
    Class<Q2> qualifierClass2,
    Class<Q3> qualifierClass3
  ) {
    return of(baseClass, Types.of(qualifierClass1), Types.of(qualifierClass2), Types.of(qualifierClass3));
  }
}
