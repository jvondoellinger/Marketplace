package zjg.marketplace.core.order.states;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.states.exceptions.IllegalStateChangeException;

public class OrderPaidState implements OrderState {
      @Override
      public void setCanceled(Order order) {
            throw new IllegalStateChangeException("This order has already been paid and cannot be canceled, only refunded.");
      }

      @Override
      public void setPending(Order order) {
            throw new IllegalStateChangeException("This order has already been paid and cannot be reopened.");
      }

      @Override
      public void setClosed(Order order) {
            throw new IllegalStateChangeException("This order has already been paid and there is no need to close it again.");
      }

      @Override
      public void setWaitingPayment(Order order) {
            throw new IllegalStateChangeException("This order has already been paid and there is no need to wait for payment again.");
      }

      @Override
      public void setPaid(Order order) {
            throw new IllegalStateChangeException("This order has already been paid.");
      }

      @Override
      public void setRefunded(Order order) {
            order.setState(null);
      }

      @Override
      public String showState() {
            return OrderStatusEnum.PAID.toString();
      }
}
