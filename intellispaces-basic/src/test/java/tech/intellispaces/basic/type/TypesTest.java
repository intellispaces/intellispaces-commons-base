package tech.intellispaces.basic.type;

import org.junit.jupiter.api.Test;
import tech.intellispaces.basic.data.Pair;
import tech.intellispaces.basic.data.Triad;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Types}, {@link ClassTypeImpl} and {@link AbstractClassType} classes.
 */
public class TypesTest {

  @Test
  public void testGet_byBaseClass() {
    // Given
    var stringClass = String.class;

    // When
    ClassType<String> type = Types.get(stringClass);

    // Then
    assertThat(type.baseClass()).isSameAs(stringClass);
    assertThat(type.qualifierTypes()).isEmpty();
  }

  @Test
  public void testGet_byBaseClassAndSingleQualifier() {
    // Given
    var listClass = List.class;
    var stringClass = String.class;

    // When
    ClassType<List<String>> type = Types.get(listClass, stringClass);

    // Then
    assertThat(type.baseClass()).isSameAs(listClass);
    assertThat(type.qualifierTypes()).hasSize(1);
    assertThat(type.qualifierTypes().get(0).asClassType().baseClass()).isSameAs(stringClass);
    assertThat(type.qualifierTypes().get(0).asClassType().qualifierTypes()).isEmpty();
  }

  @Test
  public void testGet_byBaseClassAndTwoQualifiers() {
    // Given
    var pairClass = Pair.class;
    var stringClass = String.class;
    var integerClass = Integer.class;

    // When
    ClassType<Pair<String, Integer>> type = Types.get(pairClass, stringClass, integerClass);

    // Then
    assertThat(type.baseClass()).isSameAs(pairClass);
    assertThat(type.qualifierTypes()).hasSize(2);
    assertThat(type.qualifierTypes().get(0).asClassType().baseClass()).isSameAs(stringClass);
    assertThat(type.qualifierTypes().get(0).asClassType().qualifierTypes()).isEmpty();
    assertThat(type.qualifierTypes().get(1).asClassType().baseClass()).isSameAs(integerClass);
    assertThat(type.qualifierTypes().get(1).asClassType().qualifierTypes()).isEmpty();
  }

  @Test
  public void testGet_byBaseClassAndThreeQualifiers() {
    // Given
    var triadClass = Triad.class;
    var stringClass = String.class;
    var integerClass = Integer.class;
    var booleanClass = Boolean.class;

    // When
    ClassType<Triad<String, Integer, Boolean>> type = Types.get(triadClass, stringClass, integerClass, booleanClass);

    // Then
    assertThat(type.baseClass()).isSameAs(triadClass);
    assertThat(type.qualifierTypes()).hasSize(3);
    assertThat(type.qualifierTypes().get(0).asClassType().baseClass()).isSameAs(stringClass);
    assertThat(type.qualifierTypes().get(0).asClassType().qualifierTypes()).isEmpty();
    assertThat(type.qualifierTypes().get(1).asClassType().baseClass()).isSameAs(integerClass);
    assertThat(type.qualifierTypes().get(1).asClassType().qualifierTypes()).isEmpty();
    assertThat(type.qualifierTypes().get(2).asClassType().baseClass()).isSameAs(booleanClass);
    assertThat(type.qualifierTypes().get(2).asClassType().qualifierTypes()).isEmpty();
  }

  @Test
  public void testOfList() {
    // Given
    var stringClass = String.class;

    // When
    ClassType<List<String>> type = Types.ofList(stringClass);

    // Then
    assertThat(type.baseClass()).isSameAs(List.class);
    assertThat(type.qualifierTypes()).hasSize(1);
    assertThat(type.qualifierTypes().get(0).asClassType().baseClass()).isSameAs(stringClass);
    assertThat(type.qualifierTypes().get(0).asClassType().qualifierTypes()).isEmpty();
  }

  @Test
  public void testEquals() {
    Type<String> stringType = Types.get(String.class);
    assertThat(stringType).isNotEqualTo(null);
    assertThat(stringType).isNotEqualTo("String");

    assertThat(Types.get(Integer.class)).isEqualTo(Types.get(Integer.class));

    assertThat(Types.get(List.class)).isEqualTo(Types.get(List.class));
    assertThat(Types.get(List.class, String.class)).isEqualTo(Types.get(List.class, String.class));
    assertThat(Types.get(List.class, String.class)).isNotEqualTo(Types.get(List.class, Integer.class));
    assertThat(Types.get(List.class, String.class)).isNotEqualTo(Types.get(List.class));
  }

  @Test
  public void testHashCode() {
    assertThat(Types.get(Integer.class).hashCode()).isEqualTo(Objects.hash(Integer.class, List.of()));
  }
}
