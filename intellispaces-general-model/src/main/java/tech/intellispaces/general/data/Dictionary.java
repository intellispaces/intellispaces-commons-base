package tech.intellispaces.general.data;

import java.util.List;

/**
 * The dictionary.
 */
public interface Dictionary {

  /**
   * The dictionary name.<p>
   *
   * The dictionary name can be <code>null</code>.
   */
  String name();

  /**
   * The dictionary path.
   */
  List<String> path();

  /**
   * The dictionary size.
   */
  int size();

  /**
   * Returns all property names.
   */
  List<String> propertyNames();

  boolean hasProperty(String name);

  boolean hasValue(String name);

  boolean isStringValue(String name);

  boolean isStringValue(List<String> path);

  boolean isDictionaryValue(String name);

  boolean isDictionaryValue(List<String> path);

  Object valueNullable(List<String> path);

  Integer integerValueNullable(List<String> path);

  Double doubleValueNullable(List<String> path);

  String stringValue(String name);

  String stringValueNullable(String name);

  String stringValueNullable(List<String> path);

  Dictionary dictionaryValue(String name);

  Dictionary dictionaryValueNullable(List<String> path);

  List<Integer> integerListNullable(List<String> path);

  List<Double> doubleListNullable(List<String> path);

  List<String> stringListValue(String name);

  List<String> stringListValueNullable(String name);

  List<String> stringListNullable(List<String> path);

  List<Dictionary> dictionaryListValue(String name);

  List<Dictionary> dictionaryListValueNullable(String name);

  List<Dictionary> dictionaryListNullable(List<String> path);
}
