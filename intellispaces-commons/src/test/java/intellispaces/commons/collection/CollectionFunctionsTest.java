package intellispaces.commons.collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CollectionFunctions}.
 */
public class CollectionFunctionsTest {

  @Test
  public void testAddIfNotNull() {
    // Given
    List<String> list = Mockito.spy(new ArrayList<>());
    String string = "abc";

    // When
    CollectionFunctions.addIfNotNull(list, string);
    CollectionFunctions.addIfNotNull(list, null);

    // Then
    assertThat(list).containsExactly(string);
    Mockito.verify(list, Mockito.times(1)).add(string);
  }

  @Test
  public void testJoin() {
    assertThat(CollectionFunctions.join(null)).isEmpty();
    assertThat(CollectionFunctions.join(null, 1)).containsExactly(1);
    assertThat(CollectionFunctions.join(null, 1, 2)).containsExactly(1, 2);

    assertThat(CollectionFunctions.join(List.of(1), 2, 3)).containsExactly(1, 2, 3);
    assertThat(CollectionFunctions.join(List.of(1, 2), 3, 4)).containsExactly(1, 2, 3, 4);
  }
}
