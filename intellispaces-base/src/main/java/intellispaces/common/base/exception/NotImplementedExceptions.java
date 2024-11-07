package intellispaces.common.base.exception;

import intellispaces.common.base.text.StringFunctions;

/**
 * Exception provider.
 */
public interface NotImplementedExceptions {

  static NotImplementedException withCode(String code) {
    return new NotImplementedException(
        StringFunctions.resolveTemplate("Not implemented yet ({0})", code)
    );
  }
}
