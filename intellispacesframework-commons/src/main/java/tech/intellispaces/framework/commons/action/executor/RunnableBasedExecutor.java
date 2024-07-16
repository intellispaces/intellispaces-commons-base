package tech.intellispaces.framework.commons.action.executor;

import tech.intellispaces.framework.commons.action.Executor;

public class RunnableBasedExecutor extends AbstractExecutor implements Executor {
  private final Runnable runnable;

  public RunnableBasedExecutor(Runnable runnable) {
    this.runnable = runnable;
  }

  @Override
  public Void execute() {
    runnable.run();
    return null;
  }
}
