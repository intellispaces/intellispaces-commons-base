package tech.intellispaces.commons.base.type;

import java.util.List;

/**
 * Type provider.
 */
public interface Types {

  static <T extends B, B> ClassType<T> get(Class<B> baseClass) {
    return new ClassTypeImpl<>(baseClass);
  }

  static <T extends B, B, Q> ClassType<T> get(
      Class<B> baseClass, Class<Q> qualifierClass
  ) {
    return get(baseClass, Types.get(qualifierClass));
  }

  static <T extends B, B, Q1, Q2> ClassType<T> get(
      Class<B> baseClass, Class<Q1> qualifierClass1, Class<Q2> qualifierClass2
  ) {
    return get(baseClass, Types.get(qualifierClass1), Types.get(qualifierClass2));
  }

  static <T extends B, B, Q1, Q2, Q3> ClassType<T> get(
      Class<B> baseClass,
      Class<Q1> qualifierClass1,
      Class<Q2> qualifierClass2,
      Class<Q3> qualifierClass3
  ) {
    return get(baseClass, Types.get(qualifierClass1), Types.get(qualifierClass2), Types.get(qualifierClass3));
  }

  static <T extends B, B, Q> ClassType<T> get(
    Class<B> baseClass, Type<Q> qualifierType
  ) {
    return new ClassTypeImpl<>(baseClass, List.of(qualifierType));
  }

  static <T extends B, B, Q1, Q2> ClassType<T> get(
    Class<B> baseClass, Type<Q1> qualifierType1, Type<Q2> qualifierType2
  ) {
    return new ClassTypeImpl<>(
      baseClass,
      List.of(qualifierType1, qualifierType2)
    );
  }

  static <T extends B, B, Q1, Q2, Q3> ClassType<T> get(
    Class<B> baseClass,
    Type<Q1> qualifierType1,
    Type<Q2> qualifierType2,
    Type<Q3> qualifierType3
  ) {
    return new ClassTypeImpl<>(baseClass, List.of(qualifierType1, qualifierType2, qualifierType3));
  }

  static <E> ClassType<List<E>> ofList(Class<E> elementClass) {
    return get(List.class, Types.get(elementClass));
  }
}
