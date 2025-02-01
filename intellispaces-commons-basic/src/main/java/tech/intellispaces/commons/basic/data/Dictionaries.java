package tech.intellispaces.commons.basic.data;

import tech.intellispaces.commons.basic.exception.UnexpectedExceptions;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface Dictionaries {

  static Dictionary ofProperties(String content) {
    try {
      Properties properties = new Properties();
      properties.load(new StringReader(content));
      return get(properties);
    } catch (IOException e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Could not read properties");
    }
  }

  static Dictionary get(Properties properties) {
    Map<String, Object> map = new HashMap<>();
    for (String key : properties.stringPropertyNames()) {
      if (key != null && !key.trim().isEmpty()) {
        String value = properties.getProperty(key);
        map.put(key.trim(), value != null ? value.trim() : null);
      }
    }
    return get(map);
  }

  static Dictionary get(Map<String, Object> map) {
    return get(null, map);
  }

  static Dictionary get(List<String> path, Map<String, Object> map) {
    return new MapBasedDictionary(path, map);
  }
}
