package zjg.marketplace.application.dto.order;

import zjg.marketplace.core.logging.enums.OrderStatusEnum;

public class OrderUpdateInput {
    // Constructor -------------------
    private OrderStatusEnum status;

    // Getter -------------------
    public OrderStatusEnum getStatus() {
        return status;
    }

    // Setter -------------------
    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
