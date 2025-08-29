package zjg.marketplace.core.order.states;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.states.exceptions.IllegalStateChangeException;

public class OrderWaitingPaymentState implements OrderState {
      @Override
      public void setCanceled(Order order) {
            order.setState(new OrderCanceledState());
      }

      @Override
      public void setPending(Order order) {
            throw new IllegalStateChangeException("This order cannot be opened because it is awaiting payment.");
      }

      @Override
      public void setClosed(Order order) {
            throw new IllegalStateChangeException("This order cannot be closed because it is awaiting payment.");
      }

      @Override
      public void setWaitingPayment(Order order) {
            throw new IllegalStateChangeException("This order is already awaiting payment.");
      }

      @Override
      public void setPaid(Order order) {
            order.setState(null);
      }

      @Override
      public void setRefunded(Order order) {
            throw new IllegalStateChangeException("It is not possible to refund an order that has not been paid.");
      }

      @Override
      public String showState() {
            return OrderStatusEnum.WAITING_PAYMENT.toString();
      }
}
