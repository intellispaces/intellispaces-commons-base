package tech.intellispacesframework.commons.action;

class RunnableBaseAction extends AbstractAction {
  private final Runnable runnable;

  RunnableBaseAction(Runnable runnable) {
    this.runnable = runnable;
  }

  @Override
  public void execute() {
    runnable.run();
  }
}
