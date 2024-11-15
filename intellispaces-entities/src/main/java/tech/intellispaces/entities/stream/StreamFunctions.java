package tech.intellispaces.entities.stream;

import tech.intellispaces.entities.exception.UnexpectedExceptions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Functions related to any streams.
 */
public interface StreamFunctions {

  static String readStreamAsStringForce(InputStream is, Charset charset) {
    try {
      return new String(is.readAllBytes(), charset);
    } catch (IOException e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Failed to read stream as string");
    }
  }
}
