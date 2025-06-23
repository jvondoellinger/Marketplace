package zjg.marketplace.core.entity.order;

import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.enums.OrderStatusEnum;
import zjg.marketplace.core.interfaces.compositions.builder.Reconstructable;
import zjg.marketplace.core.interfaces.compositions.clone.IClonable;
import zjg.marketplace.core.strategy.interfaces.common.IAmountGetterStrategy;
import zjg.marketplace.core.strategy.interfaces.common.IUserIdGetterStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity implements Reconstructable<OrderBuilder>,
        IClonable<Order>,
        IUserIdGetterStrategy,
        IAmountGetterStrategy {
    // Constructor ------------------------------------------------------
    protected Order() {
        init();
    }

    public Order(String userId) {
        init();
        this.buyerId = userId;
    }

    public Order(String buyerId, String firstItemId) {
        init();
        this.buyerId = buyerId;
        productsId.add(firstItemId);
    }
    public Order(String userId, List<Product> products) {
        init();
        this.buyerId = userId;
        addBatchItems(products);
    }

    // Properties ------------------------------------------------------
    /// Buyer ID
    private String buyerId; // Buyer
    private List<String> productsId;
    private OrderStatusEnum status;
    private BigDecimal amount;

    // Getter ------------------------------------------------------
    public List<String> getProductsId() {
        return productsId;
    }
    public OrderStatusEnum getStatus() {
        return status;
    }
    public String getBuyerId() {
        return buyerId;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    // Setter ------------------------------------------------------
    protected void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
    protected void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setProductsId(List<String> productsId) {
        this.productsId = productsId;
    }

    // Overrides ------------------------------------------------------
    @Override
    public OrderBuilder toBuilder() {
        return new OrderBuilder(this);
    }

    @Override
    public Order clone() {
        return null;
    }

    @Override
    public String getUserId() {
        return getBuyerId();
    }

    // Custom ------------------------------------------------------
    public void addBatchItems(List<Product> products) {
        for (var p : products) addItem(p);
    }
    public void addItem(Product product) {
        this.productsId.add(product.getId());
        this.amount = amount.add(product.getAmount());
    }
    public void removeItem(Product product) {
        this.productsId.remove(product.getId());
        this.amount = amount.subtract(product.getAmount());
    }
    public void closeOrder() {
        status = OrderStatusEnum.CLOSED;
    }
    public final BigDecimal calculateAmount(List<Product> products) {
        return products.stream()
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public void cancelOrder() {
        this.status = OrderStatusEnum.CANCELED;
    }
    public void updateStatus(OrderStatusEnum status) {
        switch (this.status) {
            case CANCELED -> throw new IllegalArgumentException("It is not possible to update the status of a canceled order!");
            case REFUNDED -> throw new IllegalArgumentException("It is not possible to update the status of a refunded order!");
        }
        this.status = status;
    }

    // Init ------------------------------------------------------------
    private void init() {
        this.amount = BigDecimal.ZERO;
        this.productsId = new ArrayList<>();
        this.status = OrderStatusEnum.PENDING;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + buyerId + '\'' +
                ", productsId=" + productsId +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }


}
