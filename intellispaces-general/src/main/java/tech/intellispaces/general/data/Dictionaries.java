package tech.intellispaces.general.data;

import java.util.LinkedHashMap;
import java.util.List;

public interface Dictionaries {

  static Dictionary get(LinkedHashMap<String, Object> map) {
    return get(null, map);
  }

  static Dictionary get(List<String> path, LinkedHashMap<String, Object> map) {
    return new MapBasedDictionaryImpl(path, map);
  }
}
