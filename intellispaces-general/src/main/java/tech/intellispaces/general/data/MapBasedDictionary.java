package tech.intellispaces.general.data;

import tech.intellispaces.general.collection.CollectionFunctions;
import tech.intellispaces.general.exception.NotImplementedExceptions;
import tech.intellispaces.general.exception.UnexpectedExceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class MapBasedDictionary implements Dictionary {
  private final String name;
  private final List<String> path;
  private final Map<String, Object> map;

  MapBasedDictionary(List<String> path, Map<String, Object> map) {
    this.path = (path != null ? path : List.of());
    this.name = (path != null && !path.isEmpty() ? path.get(path.size() - 1) : null);
    this.map = map;
  }

  @Override
  public List<String> path() {
    return path;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public List<String> propertyNames() {
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
  public boolean isStringValue(String name) {
    Object value = map.get(name);
    if (value == null) {
      return true;
    }
    return (value instanceof String);
  }

  @Override
  public boolean isStringValue(List<String> path) {
    Object destination = traverse(path);
    return (destination == null || destination instanceof String);
  }

  @Override
  public boolean isDictionaryValue(String name) {
    Object value = map.get(name);
    if (value == null) {
      return true;
    }
    return (value instanceof Map<?, ?>);
  }

  @Override
  public boolean isDictionaryValue(List<String> path) {
    Object destination = traverse(path);
    return (destination == null || destination instanceof Map<?,?>);
  }

  @Override
  public String stringValue(String name) {
    String value = stringValueNullable(name);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    return value;
  }

  @Override
  public String stringValueNullable(String name) {
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
  public List<String> stringListValue(String name) {
    List<String> list = stringListValueNullable(name);
    if (list == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    return list;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> stringListValueNullable(String name) {
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
  public Dictionary dictionaryValue(String name) {
    Object value = map.get(name);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    if (value instanceof Map<?,?>) {
      return new MapBasedDictionary(getPath(name), (Map<String, Object>) value);
    }
    if (value instanceof String) {
      return new MapBasedDictionary(
          getPath(name, (String) value),
          new HashMap<>()
      );
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", getPath(name));
  }

  @Override
  public List<Dictionary> dictionaryListValue(String name) {
    List<Dictionary> dictionaries = dictionaryListValueNullable(name);
    if (dictionaries == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(name));
    }
    return dictionaries;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Dictionary> dictionaryListValueNullable(String name) {
    Object value = map.get(name);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?> valueList) {
      return CollectionFunctions.mapEach(valueList, (v, index) -> {
        if (v instanceof String) {
          return new MapBasedDictionary(
              getPath(name, (String) v),
              new HashMap<>()
          );
        } else if (v instanceof Map) {
          var map = (Map<String, Object>) v;
          return new MapBasedDictionary(
              getPath(name, "[" + index + "]"),
              map
          );
        } else {
          throw NotImplementedExceptions.withCode("YTM0NZ");
        }
      });
    }
    if (value instanceof Map) {
      var valueMap = (Map<String, Object>) value;
      return CollectionFunctions.mapEach(valueMap.entrySet(), e -> {
        if (e.getValue() instanceof Map) {
          return new MapBasedDictionary(
              getPath(name, e.getKey()),
              (Map<String, Object>) e.getValue()
          );
        } else if (e.getValue() instanceof String) {
          return new MapBasedDictionary(
              getPath(name, e.getKey()),
              new HashMap<>()
          );
        } else {
          throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", getPath(name, e.getKey()));
        }
      });
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", getPath(name));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object valueNullable(List<String> path) {
    if (CollectionFunctions.isNullOrEmpty(path)) {
      return this;
    }
    Object result = traverse(path);
    if (result == null) {
      return null;
    } else if (result instanceof Integer) {
      return result;
    } else if (result instanceof Double) {
      return result;
    } else if (result instanceof String) {
      return result;
    } else if (result instanceof List<?> list) {
      return convertToList(list, path);
    } else if (result instanceof Map<?,?>) {
      return new MapBasedDictionary(
          getPath(path),
          (Map<String, Object>) result
      );
    } else {
      throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", path);
    }
  }

  @Override
  public Integer integerValueNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return (Integer) value;
  }

  @Override
  public Double doubleValueNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return (Double) value;
  }

  @Override
  public String stringValueNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return (String) value;
  }

  @Override
  public Dictionary dictionaryValueNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return convertToDictionary(value, path);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> stringListNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<String>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", path);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Integer> integerListNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<Integer>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", path);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Double> doubleListNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<Double>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", path);
  }

  @Override
  public List<Dictionary> dictionaryListNullable(List<String> path) {
    Object value = CollectionFunctions.isNullOrEmpty(path) ? this : traverse(path);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return converToDictionaryList((List<?>) value, path);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", path);
  }

  List<?> convertToList(List<?> list, List<String> path) {
    if (CollectionFunctions.isNullOrEmpty(list)) {
      return List.of();
    }
    Object firstElement = list.get(0);
    if (
        (firstElement instanceof Integer)
            || (firstElement instanceof Double)
            || (firstElement instanceof String)
            || (firstElement instanceof Dictionary)
    ) {
      return list;
    } else if (firstElement instanceof Map<?, ?>) {
      return converToDictionaryList(list, path);
    } else {
      throw UnexpectedExceptions.withMessage("Property '{0}' has invalid list type", path);
    }
  }

  List<Dictionary> converToDictionaryList(List<?> values, List<String> path) {
    return values.stream()
        .map(value -> convertToDictionary(value, path))
        .toList();
  }

  @SuppressWarnings("unchecked")
  Dictionary convertToDictionary(Object value, List<String> path) {
    if (value instanceof Dictionary) {
      return (Dictionary) value;
    } else if (value instanceof Map<?,?>) {
      return new MapBasedDictionary(getPath(path), (Map<String, Object>) value);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", path);
  }

  @SuppressWarnings("unchecked")
  Object traverse(List<String> path) {
    if (CollectionFunctions.isNullOrEmpty(path)) {
      return this;
    }

    Object result = null;
    Map<String, Object> curMap = map;
    List<Object> curList = null;
    for (String property : path) {
      if (curMap == null && curList == null) {
        result = null;
        break;
      } else if (curMap != null) {
        result = curMap.get(property);
        if (result == null) {
          return null;
        } else if (result instanceof Map<?, ?>) {
          curMap = (Map<String, Object>) result;
          curList = null;
        } else if (result instanceof List<?>) {
          curList = (List<Object>) result;
          curMap = null;
        } else {
          curMap = null;
          curList = null;
        }
      } if (curList != null) {
        Integer index = parseIndexProperty(property);
        if (index != null) {
          if (index < 0 || index >= curList.size()) {
            return null;
          }
          result = curList.get(index);
          if (result instanceof Map<?, ?>) {
            curMap = (Map<String, Object>) result;
            curList = null;
          } else if (result instanceof List<?>) {
            curList = (List<Object>) result;
            curMap = null;
          }
        } else {
          return null;
        }
      }
    }
    return result;
  }

  List<String> getPath(List<String> nextPath) {
    return CollectionFunctions.join(path, nextPath);
  }

  List<String> getPath(String secondPath) {
    return CollectionFunctions.join(path, secondPath);
  }

  List<String> getPath(String secondPath, String thirdPath) {
    return CollectionFunctions.join(path, secondPath, thirdPath);
  }

  Integer parseIndexProperty(String property) {
    try {
      return Integer.parseInt(property.substring(1, property.length() - 1));
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
