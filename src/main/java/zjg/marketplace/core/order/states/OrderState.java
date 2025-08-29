package zjg.marketplace.core.order.states;

import zjg.marketplace.core.order.entity.Order;

public interface OrderState {
      void setCanceled(Order order);
      void setPending(Order order);
      void setClosed(Order order);
      void setWaitingPayment(Order order);
      void setPaid(Order order);
      void setRefunded(Order order);

      String showState();
}
