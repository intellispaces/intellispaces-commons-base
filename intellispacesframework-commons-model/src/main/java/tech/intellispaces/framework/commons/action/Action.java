package tech.intellispaces.framework.commons.action;

import java.util.function.Function;

/**
 * Action.<p/>
 *
 * Action is any activity that can be executed. Actions are lightweight superstructure over Java functions, suppliers
 * and consumers.<p/>
 *
 * The action is an object. Each action object has 'execute' method to perform activity.<p/>
 */
public interface Action<V, D1, D2, D3, D4, D5> {

  V execute(D1 data1, D2 data2, D3 data3, D4 data4, D5 data5);

  <A extends Action<_V, _D1, _D2, _D3, _D4, _D5>, _V, _D1, _D2, _D3, _D4, _D5> A wrapAction(
      Function<Action<V, D1, D2, D3, D4, D5>, A> wrapperFactory
  );
}
