package tech.intellispaces.general.data;

import java.util.List;
import java.util.Map;

public interface Dictionaries {

  static Dictionary get(Map<String, Object> map) {
    return get(null, map);
  }

  static Dictionary get(List<String> path, Map<String, Object> map) {
    return new MapBasedDictionaryImpl(path, map);
  }
}
