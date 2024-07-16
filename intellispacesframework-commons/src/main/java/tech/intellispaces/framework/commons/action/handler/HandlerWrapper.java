package tech.intellispaces.framework.commons.action.handler;

import tech.intellispaces.framework.commons.action.Action;

public class HandlerWrapper<D> extends AbstractHandler<D> {
  private final Action<D, D, Void, Void, Void, Void> action;

  public HandlerWrapper(Action<D, D, Void, Void, Void, Void> action) {
    this.action = action;
  }

  @Override
  public void handle(D data) {
    action.execute(data, null, null, null, null);
  }
}
