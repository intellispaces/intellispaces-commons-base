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

  boolean hasProperty(String propertyName);

  boolean hasValue(String propertyName);

  boolean isStringValue(String propertyName);

  boolean isStringValue(List<String> propertyPath);

  boolean isDictionaryValue(String propertyName);

  boolean isDictionaryValue(List<String> propertyPath);

  Object valueNullable(String propertyName);

  Object valueNullable(List<String> propertyPath);

  Integer integerValueNullable(List<String> propertyPath);

  Double doubleValueNullable(List<String> propertyPath);

  String stringValue(String propertyName);

  String stringValueNullable(String propertyName);

  String stringValueNullable(List<String> propertyPath);

  Dictionary dictionaryValue(String propertyName);

  Dictionary dictionaryValueNullable(List<String> propertyPath);

  List<Integer> integerListNullable(List<String> propertyPath);

  List<Double> doubleListNullable(List<String> propertyPath);

  List<String> stringListValue(String propertyName);

  List<String> stringListValueNullable(String propertyName);

  List<String> stringListNullable(List<String> propertyPath);

  List<Dictionary> dictionaryListValue(String propertyName);

  List<Dictionary> dictionaryListValueNullable(String propertyName);

  List<Dictionary> dictionaryListNullable(List<String> propertyPath);
}
