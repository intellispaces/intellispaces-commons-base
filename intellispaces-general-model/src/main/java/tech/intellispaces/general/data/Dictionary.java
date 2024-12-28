package tech.intellispaces.general.data;

import java.util.List;

/**
 * The dictionary.
 */
public interface Dictionary {

  /**
   * The dictionary path.
   */
  List<String> path();

  /**
   * The dictionary name.
   * <p>
   * The dictionary name can be <code>null</code>.
   */
  String name();

  int size();

  /**
   * Returns all property names.
   */
  List<String> propertyNames();

  boolean hasProperty(String name) ;

  boolean hasValue(String name);

  String stringValue(String name);

  String stringValueNullable(String name);

  List<String> stringListValue(String name);

  List<String> stringListValueNullable(String name);

  Dictionary dictionaryValue(String name);

  List<Dictionary> dictionaryListValue(String name);

  List<Dictionary> dictionaryListValueNullable(String name);

  Object traverseNullable(List<String> path);

  Integer traverseToIntegerNullable(List<String> path);

  Double traverseToDoubleNullable(List<String> path);

  String traverseToStringNullable(List<String> path);

  Dictionary traverseToDictionaryNullable(List<String> path);

  List<String> traverseToStringListNullable(List<String> path);

  List<Integer> traverseToIntegerListNullable(List<String> path);

  List<Double> traverseToDoubleListNullable(List<String> path);

  List<Dictionary> traverseToDictionaryListNullable(List<String> path);
}
