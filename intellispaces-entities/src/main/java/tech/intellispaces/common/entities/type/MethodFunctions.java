package tech.intellispaces.common.entities.type;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

/**
 * Functions related to methods.
 */
public interface MethodFunctions {

  static Optional<Method> getMethod(Class<?> aClass, String methodName) {
    try {
      return Optional.of(aClass.getMethod(methodName));
    } catch (NoSuchMethodException e) {
      return Optional.empty();
    }
  }

  static Optional<Method> getMethod(Class<?> aClass, String name, Class<?>... parameterTypes) {
    try {
      return Optional.of(aClass.getMethod(name, parameterTypes));
    } catch (NoSuchMethodException e) {
      return Optional.empty();
    }
  }

  static boolean isAbstractMethod(Method method) {
    return Modifier.isAbstract(method.getModifiers());
  }

  static boolean isPublicMethod(Method method) {
    return Modifier.isPublic(method.getModifiers());
  }

  static boolean isStaticMethod(Method method) {
    return Modifier.isStatic(method.getModifiers());
  }
}
