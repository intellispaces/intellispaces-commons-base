package tech.intellispaces.commons.data;

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

  boolean isStringValue(String... propertyPath);

  boolean isDictionaryValue(String propertyName);

  boolean isDictionaryValue(String... propertyPath);

  Object valueNullable(String propertyName);

  Object valueNullable(String... propertyPath);

  Integer integerValueNullable(String... propertyPath);

  Double doubleValueNullable(String... propertyPath);

  String stringValue(String propertyName);

  String stringValueNullable(String propertyName);

  String stringValueNullable(String... propertyPath);

  Dictionary dictionaryValue(String propertyName);

  Dictionary dictionaryValueNullable(String... propertyPath);

  List<Integer> integerListNullable(String... propertyPath);

  List<Double> doubleListNullable(String... propertyPath);

  List<String> stringListValue(String propertyName);

  List<String> stringListValueNullable(String propertyName);

  List<String> stringListNullable(String... propertyPath);

  List<Dictionary> dictionaryListValue(String propertyName);

  List<Dictionary> dictionaryListValueNullable(String propertyName);

  List<Dictionary> dictionaryListNullable(String...propertyPath);
}
