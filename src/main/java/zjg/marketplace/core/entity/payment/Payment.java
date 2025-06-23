package zjg.marketplace.core.entity.payment;

import org.springframework.data.annotation.Transient;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.user.User;

import java.math.BigDecimal;

public abstract class Payment extends BaseEntity {
    public Payment(Order order, BigDecimal amount) {
        this.order = order;
        this.amount = amount;
    }

    @Transient
    private Order order;
    private BigDecimal amount;
    private Boolean completed;

    // Custom
    public void isCompleted() {
        this.completed = true;
    }

    // Getter
    public Boolean getCompleted() {
        return completed;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Order getOrder() {
        return order;
    }

    // Setter
    protected void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setOrder(Order order) {
        this.order = order;
    }
}
