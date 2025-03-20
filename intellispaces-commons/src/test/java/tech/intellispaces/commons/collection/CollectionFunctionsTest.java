package tech.intellispaces.commons.collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tech.intellispaces.commons.function.ThrowingBiFunction;
import tech.intellispaces.commons.function.ThrowingConsumer;
import tech.intellispaces.commons.function.ThrowingFunction;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link CollectionFunctions}.
 */
public class CollectionFunctionsTest {

  @Test
  public void testIsNullOrEmpty() {
    assertThat(CollectionFunctions.isNullOrEmpty(null)).isTrue();
    assertThat(CollectionFunctions.isNullOrEmpty(List.of())).isTrue();
    assertThat(CollectionFunctions.isNullOrEmpty(List.of(1))).isFalse();
  }

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
  public void testJoin_whenCollection() {
    assertThat(CollectionFunctions.join((Collection<Integer>) null)).isEmpty();
    assertThat(CollectionFunctions.join((Collection<Integer>) null, 1)).containsExactly(1);
    assertThat(CollectionFunctions.join((Collection<Integer>) null, 1, 2)).containsExactly(1, 2);

    assertThat(CollectionFunctions.join((Collection<Integer>) List.of(1), 2, 3)).containsExactly(1, 2, 3);
    assertThat(CollectionFunctions.join((Collection<Integer>) List.of(1, 2), 3, 4)).containsExactly(1, 2, 3, 4);
  }

  @Test
  public void testJoin_whenList() {
    assertThat(CollectionFunctions.join((List<Integer>) null)).isEmpty();
    assertThat(CollectionFunctions.join((List<Integer>) null, 1)).containsExactly(1);
    assertThat(CollectionFunctions.join((List<Integer>) null, 1, 2)).containsExactly(1, 2);

    assertThat(CollectionFunctions.join(List.of(1), 2, 3)).containsExactly(1, 2, 3);
    assertThat(CollectionFunctions.join(List.of(1, 2), 3, 4)).containsExactly(1, 2, 3, 4);

    assertThat(CollectionFunctions.join((List<Integer>) null, List.of(1))).containsExactly(1);
    assertThat(CollectionFunctions.join(List.of(1), (List<Integer>) null)).containsExactly(1);
    assertThat(CollectionFunctions.join(List.of(1), List.of(2, 3))).containsExactly(1, 2, 3);
  }

  @Test
  public void testMapEach_whenThrowingFunction() throws Exception {
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
      assertThatThrownBy(() -> CollectionFunctions.mapEach(List.of(is3), function))
          .isInstanceOf(IOException.class);
    }
  }

  @Test
  public void testMapEach_whenThrowingBiFunction() throws Exception {
    ThrowingBiFunction<String, Integer, String, Exception> function = (String s, Integer index) -> {
      if (s.isEmpty()) {
        throw new Exception("Empty stream");
      }
      return s + index;
    };

    assertThat(CollectionFunctions.mapEach(null, function)).isNull();
    assertThat(CollectionFunctions.mapEach(List.of("a", "b"), function)).containsExactly("a0", "b1");
    assertThatThrownBy(() -> CollectionFunctions.mapEach(List.of("a", ""), function))
        .isInstanceOf(Exception.class)
        .hasMessage("Empty stream");
  }

  @Test
  public void testForEach() {
    ThrowingConsumer<String, Exception> consumer = (String string) -> {
      if (string.isEmpty()) {
        throw new Exception("Empty string");
      }
    };

    assertThatCode(() -> CollectionFunctions.forEach(null, consumer)).doesNotThrowAnyException();
    assertThatThrownBy(() -> CollectionFunctions.forEach(List.of("", "a"), consumer))
        .isInstanceOf(Exception.class)
        .hasMessage("Empty string");
  }

  @Test
  public void testToList_whenEnumeration() {
    assertThat(CollectionFunctions.toList((Enumeration<String>) null)).isNull();
    assertThat(CollectionFunctions.toList(new StringTokenizer("a b c"))).containsExactly("a", "b", "c");
  }

  @Test
  public void testToList_whenIterator() {
    assertThat(CollectionFunctions.toList((Iterator<String>) null)).isNull();
    assertThat(CollectionFunctions.toList(List.of("a", "b", "c").iterator())).containsExactly("a", "b", "c");
  }
}
