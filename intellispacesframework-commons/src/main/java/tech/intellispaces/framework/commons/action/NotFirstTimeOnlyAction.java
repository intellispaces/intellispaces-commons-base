package tech.intellispaces.framework.commons.action;

class NotFirstTimeOnlyAction extends AbstractAction implements Action {
  private final Action action;
  private boolean first = true;

  NotFirstTimeOnlyAction(Action action) {
    this.action = action;
  }

  @Override
  public void execute() {
    if (!first) {
      action.execute();
    }
    first = false;
  }

  @Override
  public Action firstTimeOnly() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Action notFirstTimeOnly() {
    return this;
  }
}
