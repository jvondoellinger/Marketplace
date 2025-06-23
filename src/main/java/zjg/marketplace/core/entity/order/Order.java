package zjg.marketplace.core.entity.order;

import org.springframework.data.annotation.Transient;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.enums.OrderStatusEnum;
import zjg.marketplace.core.interfaces.builder.Reconstructable;
import zjg.marketplace.core.interfaces.clone.IClonable;
import zjg.marketplace.core.interfaces.helper.ISelfUpdatable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity implements Reconstructable<Order>, IClonable<Order>, ISelfUpdatable<Order> {
    public Order() {
        this.products = new ArrayList<>();
        this.status = OrderStatusEnum.PENDING;
    }

    public Order(User buyer) {
        super();
        this.buyer = buyer;
    }

    // Properties ------------------------------------------------------
    @Transient
    private User buyer; // Buyer
    private List<Product> products;
    private OrderStatusEnum status;

    // Getter ------------------------------------------------------
    public List<Product> getProducts() {
        return products;
    }
    public OrderStatusEnum getStatus() {
        return status;
    }
    public User getBuyer() {
        return buyer;
    }

    // Setter ------------------------------------------------------
    protected void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
    protected void setProducts(List<Product> products) {
        this.products = products;
    }
    protected void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    // Custom ------------------------------------------------------
    public void addItem(Product product) {
        this.products.add(product);
    }
    public void removeItem(Product product) {
        this.products.remove(product);
    }
    public void closeOrder() {
        status = OrderStatusEnum.CLOSED;
    }
    public final BigDecimal calculateAmount() {
        return products.stream()
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Overrides ------------------------------------------------------
    @Override
    public Order toBuilder() {
        return null;
    }
    @Override
    public Order clone() {
        return null;
    }
    @Override
    public Order selfUpdate(Order partial) {
        return null;
    }

}
