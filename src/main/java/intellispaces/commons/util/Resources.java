package intellispaces.commons.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public interface Resources {

  /**
   * Read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return string or empty optional if no resource with given name is found or resource can't be loaded.
   * @throws IOException throws if any I/O error occurs.
   */
  static Optional<String> resourceToString(Class<?> aClass, String name) throws IOException {
    var is = aClass.getResourceAsStream(name);
    if (is == null) {
      return Optional.empty();
    }
    return Optional.of(new String(is.readAllBytes(), StandardCharsets.UTF_8));
  }

  /**
   * Read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return string or empty optional if no resource with given name is found or resource can't be loaded or if any I/O error occurs..
   */
  static Optional<String> resourceToStringSilently(Class<?> aClass, String name) {
    try {
      return resourceToString(aClass, name);
    } catch (IOException e) {
      return Optional.empty();
    }
  }
}
