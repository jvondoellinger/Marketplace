package zjg.marketplace.core.order.states;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.states.exceptions.IllegalStateChangeException;

public class OrderClosedState implements OrderState {
      public OrderClosedState() {
      }

      @Override
      public void setCanceled(Order order) {
            order.setState(new OrderCanceledState());
      }

      @Override
      public void setPending(Order order) {
            throw new IllegalStateChangeException("It is not possible to reopen a closed order");
      }

      @Override
      public void setClosed(Order order) {
            throw new IllegalStateChangeException("This order cannot be closed because it has already been closed");
      }

      @Override
      public void setWaitingPayment(Order order) {
            order.setState(new OrderWaitingPaymentState());
      }

      @Override
      public void setPaid(Order order) {
            throw new IllegalStateChangeException("It is not possible to mark the order status as 'paid' directly on a closed order.");
      }

      @Override
      public void setRefunded(Order order) {
            throw new IllegalStateChangeException("It is not possible to refund an order that has not been paid.");
      }

      @Override
      public String showState() {
            return OrderStatusEnum.CLOSED.toString();
      }
}
