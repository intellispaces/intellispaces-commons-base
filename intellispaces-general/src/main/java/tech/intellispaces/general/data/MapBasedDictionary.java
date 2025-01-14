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
  public boolean hasProperty(String propertyName) {
    return map.containsKey(propertyName);
  }

  @Override
  public boolean hasValue(String propertyName) {
    return map.get(propertyName) != null;
  }

  @Override
  public boolean isStringValue(String propertyName) {
    Object value = map.get(propertyName);
    if (value == null) {
      return true;
    }
    return (value instanceof String);
  }

  @Override
  public boolean isStringValue(List<String> propertyPath) {
    Object destination = traverse(propertyPath);
    return (destination == null || destination instanceof String);
  }

  @Override
  public boolean isDictionaryValue(String propertyName) {
    Object value = map.get(propertyName);
    if (value == null) {
      return true;
    }
    return (value instanceof Map<?, ?>);
  }

  @Override
  public boolean isDictionaryValue(List<String> propertyPath) {
    Object destination = traverse(propertyPath);
    return (destination == null || destination instanceof Map<?,?>);
  }

  @Override
  public String stringValue(String propertyName) {
    String value = stringValueNullable(propertyName);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(propertyName));
    }
    return value;
  }

  @Override
  public String stringValueNullable(String propertyName) {
    Object value = map.get(propertyName);
    if (value == null) {
      return null;
    }
    if (value instanceof String) {
      return (String) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not string", getPath(propertyName));
  }

  @Override
  public List<String> stringListValue(String propertyName) {
    List<String> list = stringListValueNullable(propertyName);
    if (list == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(propertyName));
    }
    return list;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> stringListValueNullable(String propertyName) {
    Object value = map.get(propertyName);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<String>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", getPath(propertyName));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Dictionary dictionaryValue(String propertyName) {
    Object value = map.get(propertyName);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(propertyName));
    }
    if (value instanceof Map<?,?>) {
      return new MapBasedDictionary(getPath(propertyName), (Map<String, Object>) value);
    }
    if (value instanceof String) {
      return new MapBasedDictionary(
          getPath(propertyName, (String) value),
          new HashMap<>()
      );
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", getPath(propertyName));
  }

  @Override
  public List<Dictionary> dictionaryListValue(String propertyName) {
    List<Dictionary> dictionaries = dictionaryListValueNullable(propertyName);
    if (dictionaries == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", getPath(propertyName));
    }
    return dictionaries;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Dictionary> dictionaryListValueNullable(String propertyName) {
    Object value = map.get(propertyName);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?> valueList) {
      return CollectionFunctions.mapEach(valueList, (v, index) -> {
        if (v instanceof String) {
          return new MapBasedDictionary(
              getPath(propertyName, (String) v),
              new HashMap<>()
          );
        } else if (v instanceof Map) {
          var map = (Map<String, Object>) v;
          return new MapBasedDictionary(
              getPath(propertyName, "[" + index + "]"),
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
              getPath(propertyName, e.getKey()),
              (Map<String, Object>) e.getValue()
          );
        } else if (e.getValue() instanceof String) {
          return new MapBasedDictionary(
              getPath(propertyName, e.getKey()),
              new HashMap<>()
          );
        } else {
          throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", getPath(propertyName, e.getKey()));
        }
      });
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", getPath(propertyName));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object valueNullable(List<String> propertyPath) {
    if (CollectionFunctions.isNullOrEmpty(propertyPath)) {
      return this;
    }
    Object result = traverse(propertyPath);
    if (result == null) {
      return null;
    } else if (result instanceof Integer) {
      return result;
    } else if (result instanceof Double) {
      return result;
    } else if (result instanceof String) {
      return result;
    } else if (result instanceof List<?> list) {
      return convertToList(list, propertyPath);
    } else if (result instanceof Map<?,?>) {
      return new MapBasedDictionary(
          getPath(propertyPath),
          (Map<String, Object>) result
      );
    } else {
      throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", propertyPath);
    }
  }

  @Override
  public Integer integerValueNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return (Integer) value;
  }

  @Override
  public Double doubleValueNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return (Double) value;
  }

  @Override
  public String stringValueNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return (String) value;
  }

  @Override
  public Dictionary dictionaryValueNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return convertToDictionary(value, propertyPath);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> stringListNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<String>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPath);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Integer> integerListNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<Integer>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPath);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Double> doubleListNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<Double>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPath);
  }

  @Override
  public List<Dictionary> dictionaryListNullable(List<String> propertyPath) {
    Object value = CollectionFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return converToDictionaryList((List<?>) value, propertyPath);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPath);
  }

  List<?> convertToList(List<?> list, List<String> propertyPath) {
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
      return converToDictionaryList(list, propertyPath);
    } else {
      throw UnexpectedExceptions.withMessage("Property '{0}' has invalid list type", propertyPath);
    }
  }

  List<Dictionary> converToDictionaryList(List<?> values, List<String> propertyPath) {
    return values.stream()
        .map(value -> convertToDictionary(value, propertyPath))
        .toList();
  }

  @SuppressWarnings("unchecked")
  Dictionary convertToDictionary(Object value, List<String> propertyPath) {
    if (value instanceof Dictionary) {
      return (Dictionary) value;
    } else if (value instanceof Map<?,?>) {
      return new MapBasedDictionary(getPath(propertyPath), (Map<String, Object>) value);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", propertyPath);
  }

  @SuppressWarnings("unchecked")
  Object traverse(List<String> propertyPath) {
    if (CollectionFunctions.isNullOrEmpty(propertyPath)) {
      return this;
    }

    Object result = null;
    Map<String, Object> curMap = map;
    List<Object> curList = null;
    for (String propertyName : propertyPath) {
      if (curMap == null && curList == null) {
        result = null;
        break;
      } else if (curMap != null) {
        result = curMap.get(propertyName);
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
        Integer index = parseIndexProperty(propertyName);
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

  Integer parseIndexProperty(String propertyName) {
    try {
      return Integer.parseInt(propertyName.substring(1, propertyName.length() - 1));
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
