package zjg.marketplace.core.entity.order;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.List;

public class OrderBuilder {
    private final Order order;
    private OrderBuilder() {
        this.order = new Order();
    }
    protected OrderBuilder(Order order) {
        this.order = order;
    }
    public static OrderBuilder builder() {
        return new OrderBuilder();
    }
    public OrderBuilder buyerId(String buyerId) {
        order.setBuyerId(buyerId);
        return this;
    }
    /// To safe creation, use products(List<> products)!
    public OrderBuilder productsId(List<String> products) {
        order.setProductsId(products);
        return this;
    }
    public OrderBuilder products(List<Product> products) {
        order.addBatchItems(products);
        return this;
    }
    public OrderBuilder status(OrderStatusEnum status) {
        order.setStatus(status);
        return this;
    }
    public OrderBuilder amount(BigDecimal amount) {
        order.setAmount(amount);
        return this;
    }
    public Order build() {
        return order;
    }
}
