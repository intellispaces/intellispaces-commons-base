package intellispaces.common.base.stream;

import intellispaces.common.base.exception.UnexpectedViolationException;

import java.util.Optional;
import java.util.stream.Collector;

/**
 * Additional stream collectors.
 */
public interface Collectors {

  static <T> Collector<T, ?, T> one() {
    return java.util.stream.Collectors.collectingAndThen(
        java.util.stream.Collectors.toList(),
        list -> {
          if (list.size() != 1) {
            throw UnexpectedViolationException.withMessage("Expected stream with one element");
          }
          return list.get(0);
        }
    );
  }

  static <T> Collector<T, ?, Optional<T>> optional() {
    return java.util.stream.Collectors.collectingAndThen(
        java.util.stream.Collectors.toList(),
        list -> {
          if (list.isEmpty()) {
            return Optional.empty();
          } else if (list.size() == 1) {
            return Optional.of(list.get(0));
          } else {
            throw UnexpectedViolationException.withMessage("Expected stream with one element");
          }
        }
    );
  }
}
