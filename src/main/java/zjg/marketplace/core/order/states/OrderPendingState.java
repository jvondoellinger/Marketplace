package zjg.marketplace.core.order.states;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.states.exceptions.IllegalStateChangeException;

public class OrderPendingState implements OrderState {
      public OrderPendingState() {}

      @Override
      public void setCanceled(Order order) {
            order.setState(new OrderCanceledState());
      }

      @Override
      public void setPending(Order order) {
            throw new IllegalStateChangeException("The order already pending...");
      }

      @Override
      public void setClosed(Order order) {
            order.setState(new OrderClosedState());
      }

      @Override
      public void setWaitingPayment(Order order) {
            throw new IllegalStateChangeException("It is not possible to change the order status to the payment stage of an open order.");
      }

      @Override
      public void setPaid(Order order) {
            throw new IllegalStateChangeException("You cannot change an order to 'awaiting payment' if the order is open..");
      }

      @Override
      public void setRefunded(Order order) {
            throw new IllegalStateChangeException("It is not possible to refund an order that has not been paid.");
      }

      @Override
      public String showState() {
            return OrderStatusEnum.PENDING.toString();
      }
}
