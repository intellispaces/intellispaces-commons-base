package tech.intellispaces.framework.commons.action;

public abstract class AbstractAction implements Action {

  @Override
  public Action join(Action action) {
    return new MultiAction(this, action);
  }

  @Override
  public Action firstTimeOnly() {
    return new FirstTimeOnlyAction(this);
  }

  @Override
  public Action notFirstTimeOnly() {
    return new NotFirstTimeOnlyAction(this);
  }
}
