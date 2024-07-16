package tech.intellispaces.framework.commons.action;

public interface Action0<V> extends Action<V, Void, Void, Void, Void, Void> {

  /**
   * Executes action.
   */
  V execute();

  @Override
  default V execute(Void data1, Void data2, Void data3, Void data4, Void data5) {
    return execute();
  }
}
