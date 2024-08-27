package intellispaces.commons.collection;

import intellispaces.commons.exception.UnexpectedViolationException;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface AdditionalCollectors {

  static <T> Collector<T, ?, T> one() {
    return Collectors.collectingAndThen(
        Collectors.toList(),
        list -> {
          if (list.size() != 1) {
            throw UnexpectedViolationException.withMessage("Expected stream with one element");
          }
          return list.get(0);
        }
    );
  }

  static <T> Collector<T, ?, Optional<T>> optional() {
    return Collectors.collectingAndThen(
        Collectors.toList(),
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
