package intellispaces.common.base.collection;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Streams {

  static <T> Stream<T> get(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }
}
