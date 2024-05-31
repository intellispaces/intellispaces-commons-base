package tech.intellispaces.framework.commons.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.intellispaces.framework.commons.exception.UnexpectedViolationException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Resources processing functions.
 */
public class ResourceFunctions {

  private static final Logger LOG = LoggerFactory.getLogger(ResourceFunctions.class);

  /**
   * Read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return string or empty optional if no resource with given name is found.
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
   * @return string or empty optional if no resource with given name is found.
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
   * Force read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return resource string representation.
   * @throws UnexpectedViolationException throws if no resource with given name is found or resource can't be loaded.
   */
  public static String readResourceAsStringForce(Class<?> aClass, String name) {
    return readResourceAsStringForce(aClass, name, StandardCharsets.UTF_8);
  }

  /**
   * Force read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @param charset string charset.
   * @return resource string representation.
   * @throws UnexpectedViolationException throws if no resource with given name is found or resource can't be loaded.
   */
  public static String readResourceAsStringForce(Class<?> aClass, String name, Charset charset) {
    var is = aClass.getResourceAsStream(name);
    if (is == null) {
      throw UnexpectedViolationException.withMessage("Resource by name {} is not found", name);
    }
    try {
      return new String(is.readAllBytes(), charset);
    } catch (IOException e) {
      throw UnexpectedViolationException.withCauseAndMessage(e, "Failed to read resource by name {}", name);
    }
  }

  /**
   * Silently read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return string or empty optional if no resource with given name is found or resource can't be loaded.
   */
  public static Optional<String> readResourceAsStringSilently(Class<?> aClass, String name) {
    return readResourceAsStringSilently(aClass, name, StandardCharsets.UTF_8);
  }

  /**
   * Silently read resource as string.
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
