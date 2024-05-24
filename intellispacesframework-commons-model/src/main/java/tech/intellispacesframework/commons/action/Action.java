package tech.intellispacesframework.commons.action;

/**
 * Action.
 *
 * <p>Action is an activity that can be executed.
 *
 * <p>The action is an object. Each action object has 'execute' method to perform activity.
 */
@FunctionalInterface
public interface Action {

  /**
   * Executes action.
   */
  void execute();

  /**
   * Joins this action with other action.
   *
   * @param action other action.
   * @return result action.
   */
  default Action join(Action action) {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns action that executes this action first time only.
   */
  default Action firstTimeOnly() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns action that executes this action not first time only.
   */
  default Action notFirstTimeOnly() {
    throw new UnsupportedOperationException();
  }
}
