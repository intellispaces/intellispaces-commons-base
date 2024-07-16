package tech.intellispaces.framework.commons.action;

public interface Action1<V, D> extends Action<V, D, Void, Void, Void, Void> {

  /**
   * Executes action.
   */
  V execute(D data);

  @Override
  default V execute(D data1, Void data2, Void data3, Void data4, Void data5) {
    return execute(data1);
  }
}
