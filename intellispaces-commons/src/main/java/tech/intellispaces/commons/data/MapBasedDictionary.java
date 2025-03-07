package tech.intellispaces.commons.data;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.collection.CollectionFunctions;
import tech.intellispaces.commons.exception.NotImplementedExceptions;
import tech.intellispaces.commons.exception.UnexpectedExceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  public boolean isStringValue(String... propertyPath) {
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
  public boolean isDictionaryValue(String... propertyPath) {
    Object destination = traverse(propertyPath);
    return (destination == null || destination instanceof Map<?,?>);
  }

  @Override
  public String stringValue(String propertyName) {
    String value = stringValueNullable(propertyName);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", fullPropertyPath(propertyName));
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
    throw UnexpectedExceptions.withMessage("Property '{0}' is not string", fullPropertyPath(propertyName));
  }

  @Override
  public List<String> stringListValue(String propertyName) {
    List<String> list = stringListValueNullable(propertyName);
    if (list == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", fullPropertyPath(propertyName));
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
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", fullPropertyPath(propertyName));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Dictionary dictionaryValue(String propertyName) {
    Object value = map.get(propertyName);
    if (value == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", fullPropertyPath(propertyName));
    }
    if (value instanceof Map<?,?>) {
      return new MapBasedDictionary(fullPropertyPath(propertyName), (Map<String, Object>) value);
    }
    if (value instanceof String) {
      return new MapBasedDictionary(
          fullPropertyPath(propertyName, (String) value),
          new HashMap<>()
      );
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", fullPropertyPath(propertyName));
  }

  @Override
  public List<Dictionary> dictionaryListValue(String propertyName) {
    List<Dictionary> dictionaries = dictionaryListValueNullable(propertyName);
    if (dictionaries == null) {
      throw UnexpectedExceptions.withMessage("Property '{0}' is not found", fullPropertyPath(propertyName));
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
              fullPropertyPath(propertyName, (String) v),
              new HashMap<>()
          );
        } else if (v instanceof Map) {
          var map = (Map<String, Object>) v;
          return new MapBasedDictionary(
              fullPropertyPath(propertyName, "[" + index + "]"),
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
              fullPropertyPath(propertyName, e.getKey()),
              (Map<String, Object>) e.getValue()
          );
        } else if (e.getValue() instanceof String) {
          return new MapBasedDictionary(
              fullPropertyPath(propertyName, e.getKey()),
              new HashMap<>()
          );
        } else {
          throw UnexpectedExceptions.withMessage("Property '{0}' is not dictionary", fullPropertyPath(propertyName, e.getKey()));
        }
      });
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", fullPropertyPath(propertyName));
  }

  @Override
  public Object valueNullable(String propertyName) {
    return convert(map.get(propertyName), propertyName);
  }

  @Override
  public Object valueNullable(String... propertyPath) {
    if (ArraysFunctions.isNullOrEmpty(propertyPath)) {
      return this;
    }
    Object value = traverse(propertyPath);
    return convert(value, propertyPath);
  }

  @Override
  public Integer integerValueNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return (Integer) value;
  }

  @Override
  public Double doubleValueNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return (Double) value;
  }

  @Override
  public String stringValueNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return (String) value;
  }

  @Override
  public Dictionary dictionaryValueNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    return convertToDictionary(value, propertyPath);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> stringListNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<String>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPathString(propertyPath));
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Integer> integerListNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<Integer>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPathString(propertyPath));
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Double> doubleListNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<Double>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPathString(propertyPath));
  }

  @Override
  public List<Dictionary> dictionaryListNullable(String... propertyPath) {
    Object value = ArraysFunctions.isNullOrEmpty(propertyPath) ? this : traverse(propertyPath);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return converToDictionaryList((List<?>) value, propertyPath);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", propertyPathString(propertyPath));
  }

  @SuppressWarnings("unchecked")
  Object convert(Object value, String... propertyPath) {
    if (value == null) {
      return null;
    } else if (value instanceof Integer) {
      return value;
    } else if (value instanceof Double) {
      return value;
    } else if (value instanceof String) {
      return value;
    } else if (value instanceof List<?> list) {
      return convertToList(list, propertyPath);
    } else if (value instanceof Map<?, ?>) {
      return new MapBasedDictionary(fullPropertyPath(propertyPath), (Map<String, Object>) value);
    } else {
      throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", propertyPathString(propertyPath));
    }
  }

  List<?> convertToList(List<?> list, String... propertyPath) {
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
      throw UnexpectedExceptions.withMessage("Property '{0}' has invalid list type", propertyPathString(propertyPath));
    }
  }

  List<Dictionary> converToDictionaryList(List<?> values, String... propertyPath) {
    return CollectionFunctions.mapEach(values, value -> convertToDictionary(value, propertyPath));
  }

  @SuppressWarnings("unchecked")
  Dictionary convertToDictionary(Object value, String... propertyPath) {
    if (value instanceof Dictionary) {
      return (Dictionary) value;
    } else if (value instanceof Map<?, ?>) {
      return new MapBasedDictionary(fullPropertyPath(propertyPath), (Map<String, Object>) value);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", propertyPathString(propertyPath));
  }

  @SuppressWarnings("unchecked")
  Object traverse(String... propertyPath) {
    if (ArraysFunctions.isNullOrEmpty(propertyPath)) {
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

  List<String> fullPropertyPath(String... nextPath) {
    return CollectionFunctions.join(path, nextPath);
  }

  List<String> fullPropertyPath(String secondPath) {
    return CollectionFunctions.join(path, secondPath);
  }

  List<String> fullPropertyPath(String secondPath, String thirdPath) {
    return CollectionFunctions.join(path, secondPath, thirdPath);
  }

  String propertyPathString(String... path) {
    if (ArraysFunctions.isNullOrEmpty(path)) {
      return "";
    }
    return String.join("\\", path);
  }

  Integer parseIndexProperty(String propertyName) {
    try {
      return Integer.parseInt(propertyName.substring(1, propertyName.length() - 1));
    } catch (NumberFormatException e) {
      return null;
    }
  }
}
