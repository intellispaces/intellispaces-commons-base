package tech.intellispaces.framework.commons.action;

class FirstTimeOnlyAction extends AbstractAction implements Action {
  private final Action action;
  private boolean first = true;

  FirstTimeOnlyAction(Action action) {
    this.action = action;
  }

  @Override
  public void execute() {
    if (first) {
      action.execute();
    }
    first = false;
  }

  @Override
  public Action firstTimeOnly() {
    return this;
  }

  @Override
  public Action notFirstTimeOnly() {
    throw new UnsupportedOperationException();
  }
}
