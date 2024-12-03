package tech.intellispaces.general.exception;

import tech.intellispaces.general.text.StringFunctions;

/**
 * Provider of the exception {@link NotImplementedException}.
 */
public interface NotImplementedExceptions {

  static NotImplementedException withCode(String code) {
    return new NotImplementedException(StringFunctions.resolveTemplate("Not implemented yet ({0})",
        code
    ));
  }

  static NotImplementedException withCodeAndMessage(String code, String message) {
    return new NotImplementedException(StringFunctions.resolveTemplate("Not implemented yet ({0}). {1}",
        code,
        message
    ));
  }

  static NotImplementedException withCodeAndMessage(String code, String template, Object... params) {
    return new NotImplementedException(StringFunctions.resolveTemplate("Not implemented yet ({0}). {1}",
        code,
        StringFunctions.resolveTemplate(template, params)
    ));
  }
}
