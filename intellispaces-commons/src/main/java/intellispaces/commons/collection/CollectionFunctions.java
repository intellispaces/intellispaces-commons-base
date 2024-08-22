package intellispaces.commons.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Collection processing functions.
 */
public interface CollectionFunctions {

  static <T> void addIfNotNull(List<T> list, T value) {
    if (value != null) {
      list.add(value);
    }
  }

  static <T> Collection<T> join(Collection<T> collection, T... other) {
    if (collection == null) {
      collection = List.of();
    }

    List<T> result = new ArrayList<>(collection);
    if (other != null && other.length > 0) {
      result.addAll(Arrays.stream(other).toList());
    }
    return Collections.unmodifiableCollection(result);
  }
}
