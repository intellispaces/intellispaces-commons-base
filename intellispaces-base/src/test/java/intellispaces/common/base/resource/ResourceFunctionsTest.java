package intellispaces.common.base.resource;

import intellispaces.common.base.exception.UnexpectedException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ResourceFunctions}.
 */
public class ResourceFunctionsTest {

  @Test
  public void testReadResourceAsStream() throws Exception {
    try (InputStream is = ResourceFunctions.readResourceAsStream(ResourceFunctionsTest.class, "/resource.txt")) {
      assertThat(is).isNotNull();
    }
  }

  @Test
  public void testReadResourceAsString_whenResourceExists() throws Exception {
    Optional<String> resource = ResourceFunctions.readResourceAsString(ResourceFunctionsTest.class, "/resource.txt");
    assertThat(resource).isPresent();
    assertThat(resource.get()).isEqualTo("The resource sample.");
  }

  @Test
  public void testReadResourceAsString_whenResourceNotExists() throws Exception {
    Optional<String> resource = ResourceFunctions.readResourceAsString(ResourceFunctionsTest.class, "/nonExistentResource.txt");
    assertThat(resource).isEmpty();
  }

  @Test
  public void testReadResourceAsStringForce_whenResourceExists() {
    String resource = ResourceFunctions.readResourceAsStringForce(ResourceFunctionsTest.class, "/resource.txt");
    assertThat(resource).isNotNull();
    assertThat(resource).isEqualTo("The resource sample.");
  }

  @Test
  public void testReadResourceAsStringForce_whenResourceNotExists() {
    ThrowableAssert.ThrowingCallable action = () -> {
      ResourceFunctions.readResourceAsStringForce(ResourceFunctionsTest.class, "/nonExistentResource.txt");
    };
    assertThatThrownBy(action).isExactlyInstanceOf(UnexpectedException.class)
        .hasMessage("Failed to read resource by name /nonExistentResource.txt");
  }

  @Test
  public void testReadResourceAsStringSilently_whenResourceExists() {
    Optional<String> resource = ResourceFunctions.readResourceAsStringSilently(ResourceFunctionsTest.class, "/resource.txt");
    assertThat(resource).isPresent();
    assertThat(resource.get()).isEqualTo("The resource sample.");
  }

  @Test
  public void testReadResourceAsStringSilently_whenResourceNotExists() {
    Optional<String> resource = ResourceFunctions.readResourceAsStringSilently(ResourceFunctionsTest.class, "/nonExistentResource.txt");
    assertThat(resource).isEmpty();
  }
}
