package intellispaces.common.base.stream;

import intellispaces.common.base.exception.UnexpectedViolationException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link StreamFunctions} class.
 */
public class StreamFunctionsTest {

  @Test
  public void testReadStreamAsStringForce_whenValidStream() {
    // Given
    String string = "This is the string";
    InputStream is = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));

    // When
    String result = StreamFunctions.readStreamAsStringForce(is, StandardCharsets.UTF_8);

    // Then
    assertThat(result).isEqualTo(string);
  }

  @Test
  public void testReadStreamAsStringForce_whenIOException() throws Exception {
    // Given
    String string = "This is the string";
    InputStream is = mock(InputStream.class);
    when(is.readAllBytes()).thenThrow(new IOException());

    // When
    ThrowableAssert.ThrowingCallable action = () -> StreamFunctions.readStreamAsStringForce(is, StandardCharsets.UTF_8);

    // Then
    assertThatThrownBy(action).isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Failed to read stream as string");
  }
}
