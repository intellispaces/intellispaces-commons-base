package tech.intellispaces.general.data;

import tech.intellispaces.general.collection.CollectionFunctions;
import tech.intellispaces.general.exception.NotImplementedExceptions;
import tech.intellispaces.general.exception.UnexpectedExceptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class MapBasedDictionaryImpl implements Dictionary {
  private final List<String> path;
  private final String name;
  private final LinkedHashMap<String, Object> map;

  MapBasedDictionaryImpl(List<String> path, LinkedHashMap<String, Object> map) {
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
    if (value instanceof LinkedHashMap<?,?>) {
      return new MapBasedDictionaryImpl(getPath(name), (LinkedHashMap<String, Object>) value);
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
    if (value instanceof LinkedHashMap) {
      var valueMap = (LinkedHashMap<String, Object>) value;
      return CollectionFunctions.mapEach(valueMap.entrySet(), e -> {
        if (e.getValue() instanceof LinkedHashMap) {
          return new MapBasedDictionaryImpl(
              getPath(name, e.getKey()),
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
              new LinkedHashMap<>()
          );
        } else if (v instanceof LinkedHashMap) {
          var map = (LinkedHashMap<String, Object>) v;
          return new MapBasedDictionaryImpl(
              getPath(name, "[" + index + "]"),
              map
          );
        } else {
          throw NotImplementedExceptions.withCode("YTM0NZ");
        }
      });
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", getPath(name));
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object traverseNullable(List<String> path) {
    if (path.isEmpty()) {
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
    } else if (result instanceof LinkedHashMap<?,?>) {
      return new MapBasedDictionaryImpl(
          getPath(path),
          (LinkedHashMap<String, Object>) result
      );
    } else {
      throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", path);
    }
  }

  @Override
  public Integer traverseToIntegerNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return (Integer) value;
  }

  @Override
  public Double traverseToDoubleNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return (Double) value;
  }

  @Override
  public String traverseToStringNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return (String) value;
  }

  @Override
  public Dictionary traverseToDictionaryNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
    if (value == null) {
      return null;
    }
    return convertToDictionary(value, path);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<String> traverseToStringListNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
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
  public List<Integer> traverseToIntegerListNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
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
  public List<Double> traverseToDoubleListNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return (List<Double>) value;
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", path);
  }

  @Override
  public List<Dictionary> traverseToDictionaryListNullable(List<String> path) {
    Object value = path.isEmpty() ? this : traverse(path);
    if (value == null) {
      return null;
    }
    if (value instanceof List<?>) {
      return converToDictionaryList((List<?>) value, path);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' is not list", path);
  }

  List<?> convertToList(List<?> list, List<String> path) {
    if (list.isEmpty()) {
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
    } else if (value instanceof LinkedHashMap<?,?>) {
      return new MapBasedDictionaryImpl(getPath(path), (LinkedHashMap<String, Object>) value);
    }
    throw UnexpectedExceptions.withMessage("Property '{0}' has invalid type", path);
  }

  @SuppressWarnings("unchecked")
  Object traverse(List<String> path) {
    if (path == null || path.isEmpty()) {
      return this;
    }

    Object result = null;
    Map<String, Object> curMap = map;
    for (String pathPart : path) {
      if (curMap == null) {
        result = null;
        break;
      } else {
        Object target = curMap.get(pathPart);
        if (target == null) {
          result = null;
          break;
        } else if (target instanceof Map) {
          result = target;
          curMap = (Map<String, Object>) target;
        } else {
          result = target;
          curMap = null;
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
}
