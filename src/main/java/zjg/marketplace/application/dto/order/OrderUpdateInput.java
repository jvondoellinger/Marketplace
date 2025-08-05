package zjg.marketplace.application.dto.order;

import zjg.marketplace.core.enums.OrderStatusEnum;

public class OrderUpdateInput extends OrderInput {
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
