package tech.intellispaces.general.collection;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tech.intellispaces.general.function.ThrowingFunction;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

  @Test
  public void testMapEach() throws Exception {
    ThrowingFunction<InputStream, Integer, IOException> function = InputStream::read;

    assertThat(CollectionFunctions.mapEach(null, function)).isNull();

    InputStream is1 = new ByteArrayInputStream("a".getBytes(StandardCharsets.UTF_8));
    InputStream is2 = new ByteArrayInputStream("b".getBytes(StandardCharsets.UTF_8));
    assertThat(CollectionFunctions.mapEach(List.of(is1, is2), function)).containsExactly(97, 98);

    try (InputStream is3 = new InputStream() {
      @Override
      public int read() throws IOException {
        throw new IOException();
      }
    }) {
      ThrowableAssert.ThrowingCallable callable = () -> CollectionFunctions.mapEach(List.of(is3), function);
      assertThatThrownBy(callable)
          .isInstanceOf(IOException.class);
      }
  }
}
