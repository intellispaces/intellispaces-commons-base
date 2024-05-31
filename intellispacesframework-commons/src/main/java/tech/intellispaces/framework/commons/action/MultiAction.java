package tech.intellispaces.framework.commons.action;

import java.util.ArrayList;
import java.util.List;

class MultiAction extends AbstractAction {
  private final List<Action> actions;

  MultiAction(Action action1, Action action2) {
    this.actions = new ArrayList<>();
    this.actions.addAll(allActionsOf(action1));
    this.actions.addAll(allActionsOf(action2));
  }

  List<Action> actions() {
    return actions;
  }

  @Override
  public void execute() {
    actions.forEach(Action::execute);
  }

  private List<Action> allActionsOf(Action action) {
    if (action instanceof MultiAction) {
      return ((MultiAction) action).actions();
    } else {
      return List.of(action);
    }
  }
}
