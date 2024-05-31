package tech.intellispaces.framework.commons.collection;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CollectionFunctions}.
 */
public class CollectionFunctionsTest {

  @Test
  public void testJoin() {
    assertThat(CollectionFunctions.join(null)).isEmpty();
    assertThat(CollectionFunctions.join(null, 1)).containsExactly(1);
    assertThat(CollectionFunctions.join(null, 1, 2)).containsExactly(1, 2);

    assertThat(CollectionFunctions.join(List.of(1), 2, 3)).containsExactly(1, 2, 3);
    assertThat(CollectionFunctions.join(List.of(1, 2), 3, 4)).containsExactly(1, 2, 3, 4);
  }
}
