package tech.intellispaces.entity.type;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Method provider.
 */
public interface Methods {

  static Optional<Method> get(Class<?> aClass, String methodName) {
    return MethodFunctions.getMethod(aClass, methodName);
  }

  static Optional<Method> get(Class<?> aClass, String name, Class<?>... parameterTypes) {
    return MethodFunctions.getMethod(aClass, name, parameterTypes);
  }
}
