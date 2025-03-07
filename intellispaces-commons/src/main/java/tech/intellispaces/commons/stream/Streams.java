package tech.intellispaces.commons.stream;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Stream provider.
 */
public interface Streams {

  static <T> Stream<T> get(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }
}
