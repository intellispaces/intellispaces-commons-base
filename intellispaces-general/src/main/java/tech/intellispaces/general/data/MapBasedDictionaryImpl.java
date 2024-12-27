package tech.intellispaces.general.data;

import tech.intellispaces.general.collection.CollectionFunctions;
import tech.intellispaces.general.exception.NotImplementedExceptions;
import tech.intellispaces.general.exception.UnexpectedExceptions;
import tech.intellispaces.general.text.StringFunctions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class MapBasedDictionaryImpl implements Dictionary {
  private final String path;
  private final String name;
  private final LinkedHashMap<String, Object> map;

  MapBasedDictionaryImpl(String path, String name, LinkedHashMap<String, Object> map) {
    this.path = path;
    this.name = name;
    this.map = map;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String path() {
    return path;
  }

  @Override
  public List<String> properties() {
    return new ArrayList<>(map.keySet());
  }

  @Override
  public boolean hasProperty(String name) {
    return map.containsKey(name);
  }

  @Override
  public boolean hasValue(String name) {
    return map.get(name) != null;
  }

  @Override
  public String readString(String name) {
    String value = readStringNullable(name);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    return value;
  }

  @Override
  public String readStringNullable(String name) {
    Object value = map.get(name);
    if (value == null) {
      return null;
    }
    if (value instanceof String) {
      return (String) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not string", getPath(name));
  }

  @Override
  public List<String> readStringList(String name) {
    List<String> list = readStringListNullable(name);
    if (list == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    return list;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> readStringListNullable(String name) {
    Object value = map.get(name);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<String>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", getPath(name));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Dictionary readDictionary(String name) {
    Object value = map.get(name);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    if (value instanceof LinkedHashMap<?,?>) {
      return new MapBasedDictionaryImpl(getPath(name), name, (LinkedHashMap<String, Object>) value);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", getPath(name));
  }

  @Override
  public List<Dictionary> readDictionaryList(String name) {
    List<Dictionary> dictionaries = readDictionaryListNullable(name);
    if (dictionaries == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    return dictionaries;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Dictionary> readDictionaryListNullable(String name) {
    Object value = map.get(name);
    if (value == null) {
      return null;
    }
    if (value instanceof LinkedHashMap) {
      var valueMap = (LinkedHashMap<String, Object>) value;
      return CollectionFunctions.mapEach(valueMap.entrySet(), e -> {
        if (e.getValue() instanceof LinkedHashMap) {
          return new MapBasedDictionaryImpl(
              getPath(name, e.getKey()),
              e.getKey(),
              (LinkedHashMap<String, Object>) e.getValue()
          );
        } else {
          throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", getPath(name, e.getKey()));
        }
      });
    }
    if (value instanceof List<?> valueList) {
      return CollectionFunctions.mapEach(valueList, (v, index) -> {
        if (v instanceof String) {
          return new MapBasedDictionaryImpl(
              getPath(name, (String) v),
              (String) v,
              new LinkedHashMap<>()
          );
        } else if (v instanceof LinkedHashMap) {
          var map = (LinkedHashMap<String, Object>) v;
          return new MapBasedDictionaryImpl(
              getPath(name, "[" + index + "]"),
              "[" + index + "]",
              map
          );
        } else {
          throw NotImplementedExceptions.withCode("YTM0NZ");
        }
      });
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", getPath(name));
  }

  String getPath(String secondPath) {
    return StringFunctions.join(path, secondPath, ".");
  }

  String getPath(String secondPath, String thirdPath) {
    return StringFunctions.join(path, secondPath, thirdPath, ".");
  }
}
