package intellispaces.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class ResourceFunctions {

  private static final Logger LOG = LoggerFactory.getLogger(ResourceFunctions.class);

  /**
   * Read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return string or empty optional if no resource with given name is found or resource can't be loaded.
   * @throws IOException throws if any I/O error occurs.
   */
  public static Optional<String> readResourceAsString(Class<?> aClass, String name) throws IOException {
    return readResourceAsString(aClass, name, StandardCharsets.UTF_8);
  }

  /**
   * Read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @param charset string charset.
   * @return string or empty optional if no resource with given name is found or resource can't be loaded.
   * @throws IOException throws if any I/O error occurs.
   */
  public static Optional<String> readResourceAsString(Class<?> aClass, String name, Charset charset) throws IOException {
    var is = aClass.getResourceAsStream(name);
    if (is == null) {
      return Optional.empty();
    }
    return Optional.of(new String(is.readAllBytes(), charset));
  }

  /**
   * Read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return string or empty optional if no resource with given name is found or resource can't be loaded.
   */
  public static Optional<String> readResourceAsStringSilently(Class<?> aClass, String name) {
    return readResourceAsStringSilently(aClass, name, StandardCharsets.UTF_8);
  }

  /**
   * Read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @param charset string charset.
   * @return string or empty optional if no resource with given name is found or resource can't be loaded.
   */
  public static Optional<String> readResourceAsStringSilently(Class<?> aClass, String name, Charset charset) {
    try {
      return readResourceAsString(aClass, name, charset);
    } catch (IOException e) {
      LOG.error("Error reading resource by name " + name , e);
      return Optional.empty();
    }
  }

  private ResourceFunctions() {}
}
