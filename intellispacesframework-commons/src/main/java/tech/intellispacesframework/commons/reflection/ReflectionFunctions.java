package tech.intellispacesframework.commons.reflection;

import java.lang.reflect.Field;

public interface ReflectionFunctions {

  @SuppressWarnings("unchecked")
  static <F> F getStaticField(Class<?> aClass, String fieldName, Class<F> fieldClass) throws NoSuchFieldException, IllegalAccessException {
    Field field = aClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    return (F) field.get(null);
  }

  static void setStaticField(Class<?> aClass, String fieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
    Field field = aClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(null, newValue);
  }
}
