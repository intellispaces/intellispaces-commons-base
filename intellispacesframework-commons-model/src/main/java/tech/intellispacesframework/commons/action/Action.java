package tech.intellispacesframework.commons.action;

/**
 * Action.
 *
 * <p>Action is an activity that can be executed.
 *
 * <p>The action is an object. Each action object has 'execute' method to perform activity.
 */
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
  Action join(Action action);

  /**
   * Returns action that executes this action first time only.
   */
  Action firstTimeOnly();

  /**
   * Returns action that executes this action not first time only.
   */
  Action notFirstTimeOnly();
}
