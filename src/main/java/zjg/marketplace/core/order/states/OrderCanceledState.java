package zjg.marketplace.core.order.states;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.states.exceptions.IllegalStateChangeException;

public class OrderCanceledState implements OrderState {
      public OrderCanceledState() {
      }

      @Override
      public void setCanceled(Order order) {
            throw new IllegalStateChangeException("It is not possible to cancel an order that has already been canceled...");
      }

      @Override
      public void setPending(Order order) {
            throw new IllegalStateChangeException("Unable to change order state because order has been canceled");
      }

      @Override
      public void setClosed(Order order) {
            throw new IllegalStateChangeException("Unable to change order state because order has been canceled");
      }

      @Override
      public void setWaitingPayment(Order order) {
            throw new IllegalStateChangeException("Unable to change order state because order has been canceled");
      }

      @Override
      public void setPaid(Order order) {
            throw new IllegalStateChangeException("Unable to change order state because order has been canceled");
      }

      @Override
      public void setRefunded(Order order) {
            throw new IllegalStateChangeException("Unable to change order state because order has been canceled");
      }

      @Override
      public String showState() {
            return OrderStatusEnum.CANCELED.toString();
      }
}
