package intellispaces.common.base.resource;

import intellispaces.common.base.exception.UnexpectedViolationException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link ResourceFunctions}.
 */
public class ResourceFunctionsTest {

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
    assertThatThrownBy(action).isExactlyInstanceOf(UnexpectedViolationException.class)
        .hasMessage("Resource by name /nonExistentResource.txt is not found");
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
