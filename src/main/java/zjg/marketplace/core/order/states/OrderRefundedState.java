package zjg.marketplace.core.order.states;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.states.exceptions.IllegalStateChangeException;

public class OrderRefundedState implements OrderState {
      public OrderRefundedState() {
      }

      @Override
      public void setCanceled(Order order) {
            throw new IllegalStateChangeException("It is not possible to cancel an order that has already been refunded...");
      }

      @Override
      public void setPending(Order order) {
            throw new IllegalStateChangeException("It is not possible to reopen an order that has already been refunded...");
      }

      @Override
      public void setClosed(Order order) {
            throw new IllegalStateChangeException("It is not possible to close again an order that has already been refunded...");
      }

      @Override
      public void setWaitingPayment(Order order) {
            throw new IllegalStateChangeException("It is not possible to put the order on 'awaiting payment' even though it has already been refunded...");
      }

      @Override
      public void setPaid(Order order) {
            throw new IllegalStateChangeException("It is not possible to pay again an order that has already been refunded...");
      }

      @Override
      public void setRefunded(Order order) {
            throw new IllegalStateChangeException("This order already refunded.");
      }

      @Override
      public String showState() {
            return OrderStatusEnum.REFUNDED.toString();
      }
}
