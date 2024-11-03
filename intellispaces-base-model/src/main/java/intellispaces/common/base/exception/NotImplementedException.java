package intellispaces.common.base.exception;

public class NotImplementedException extends UnexpectedViolationException {

  NotImplementedException(String messageTemplate, Object... messageParams) {
    super(messageTemplate, messageParams);
  }

  public static NotImplementedException withCode(String code) {
    return new NotImplementedException("Not implemented yet ({0})", code);
  }

  public static NotImplementedException withCodeAndMessage(
      String code, String messageTemplate, Object... messageParams
  ) {
    return new NotImplementedException("Not implemented yet (" + code + "). " + messageTemplate, messageParams);
  }
}
