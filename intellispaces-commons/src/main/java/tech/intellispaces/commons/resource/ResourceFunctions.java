package tech.intellispaces.commons.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.intellispaces.commons.exception.UnexpectedException;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.stream.StreamFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
   * Read resource as stream.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return resource stream.
   */
  public static InputStream readResourceAsStream(Class<?> aClass, String name) {
    return aClass.getResourceAsStream(name);
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
  public static Optional<String> readResourceAsString(
      Class<?> aClass, String name, Charset charset
  ) throws IOException {
    try (InputStream is = readResourceAsStream(aClass, name)) {
      if (is == null) {
        return Optional.empty();
      }
      return Optional.of(new String(is.readAllBytes(), charset));
    }
  }

  /**
   * Force read resource as string.
   *
   * @param aClass class associated with resource.
   * @param name resource name.
   * @return resource string representation.
   * @throws UnexpectedException throws if no resource with given name is found or resource can't be loaded.
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
   * @throws UnexpectedException throws if no resource with given name is found or resource can't be loaded.
   */
  public static String readResourceAsStringForce(Class<?> aClass, String name, Charset charset) {
    try (InputStream is = readResourceAsStream(aClass, name)) {
      if (is == null) {
        throw UnexpectedExceptions.withMessage("Resource by name {0} is not found", name);
      }
      return StreamFunctions.readStreamAsStringForce(is, charset);
    } catch (Exception e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Failed to read resource by name {0}", name);
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
      return Optional.of(readResourceAsStringForce(aClass, name, charset));
    } catch (Exception e) {
      LOG.error("Error reading resource by name {}", name, e);
      return Optional.empty();
    }
  }

  public static String readResourceAsString(URL url) throws IOException {
    if (url == null) {
      return null;
    }
    var sb = new StringBuilder();
    URLConnection connection = url.openConnection();
    try (var reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
      String str;
      boolean firstLine = true;
      while ((str = reader.readLine()) != null) {
        if (!firstLine) {
          sb.append(System.lineSeparator());
        }
        sb.append(str);
        firstLine = false;
      }
    }
    return sb.toString();
  }

  private ResourceFunctions() {}
}
