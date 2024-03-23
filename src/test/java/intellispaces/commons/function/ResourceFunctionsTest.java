package intellispaces.commons.function;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ResourceFunctions}.
 */
public class ResourceFunctionsTest {

  @Test
  public void testReadResourceAsString_whenResourceExists() throws Exception {
    Optional<String> resource = ResourceFunctions.readResourceAsString(ResourceFunctionsTest.class, "/resource.txt");
    assertThat(resource).isPresent();
    assertThat(resource.get()).isEqualTo("This is the resource.");
  }

  @Test
  public void testReadResourceAsString_whenResourceNotExists() throws Exception {
    Optional<String> resource = ResourceFunctions.readResourceAsString(ResourceFunctionsTest.class, "/nonExistentResource.txt");
    assertThat(resource).isEmpty();
  }

  @Test
  public void testReadResourceAsStringSilently_whenResourceExists() {
    Optional<String> resource = ResourceFunctions.readResourceAsStringSilently(ResourceFunctionsTest.class, "/resource.txt");
    assertThat(resource).isPresent();
    assertThat(resource.get()).isEqualTo("This is the resource.");
  }

  @Test
  public void testReadResourceAsStringSilently_whenResourceNotExists() {
    Optional<String> resource = ResourceFunctions.readResourceAsStringSilently(ResourceFunctionsTest.class, "/nonExistentResource.txt");
    assertThat(resource).isEmpty();
  }
}
