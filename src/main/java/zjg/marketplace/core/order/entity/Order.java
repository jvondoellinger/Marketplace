package zjg.marketplace.core.order.entity;

import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.interfaces.compositions.builder.Reconstructable;
import zjg.marketplace.core.interfaces.compositions.clone.IClonable;
import zjg.marketplace.core.order.states.OrderPendingState;
import zjg.marketplace.core.order.states.OrderState;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.strategy.interfaces.common.IAmountGetterStrategy;
import zjg.marketplace.core.strategy.interfaces.common.IUserIdGetterStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity implements Reconstructable<OrderBuilder>,
        IClonable<Order>,
        IUserIdGetterStrategy,
        IAmountGetterStrategy {
    // * Constructor ------------------------------------------------------
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

    private Order(Order order) {
        this.buyerId = order.getBuyerId();
        this.productsId = order.productsId;
        this.state = order.getState();
        this.amount = order.getAmount();
        this.setId(order.getId());
        this.setCreatedAt(order.getCreatedAt());
        this.setUpdatedAt(order.getUpdatedAt());
    }

    // * Properties ------------------------------------------------------
    private String buyerId; // Buyer
    private List<String> productsId;
/*    private OrderStatusEnum status;*/
    private OrderState state;
    private BigDecimal amount;

    // * Getter ------------------------------------------------------
    public List<String> getProductsId() {
        return productsId;
    }
    public String getBuyerId() {
        return buyerId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public OrderState getState() {
        return state;
    }

    // * Setter ------------------------------------------------------
    protected void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setProductsId(List<String> productsId) {
        this.productsId = productsId;
    }
    public void setState(OrderState state) {
        this.state = state;
    }

    // * Overrides ------------------------------------------------------
    @Override
    public OrderBuilder toBuilder() {
        return new OrderBuilder(this);
    }

    @Override
    public Order clone() {
        return new Order(this);
    }

    @Override
    public String getUserId() {
        return getBuyerId();
    } // ! Error here (serialization)

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + buyerId + '\'' +
                ", productsId=" + productsId +
                ", state=" + state.showState() +
                ", amount=" + amount +
                '}';
    }

    // * Custom ------------------------------------------------------
    // ! <Bad section
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
    // ! />

    // * Init ------------------------------------------------------------
    private void init() {
        this.amount = BigDecimal.ZERO;
        this.productsId = new ArrayList<>();
        this.state = new OrderPendingState();
    }

    // * States -----------------------------------------------------------
    public void cancel() {
        state.setCanceled(this);
    }

    public void close() {
        state.setClosed(this);
    }

    public void waitingPayment() {
        state.setWaitingPayment(this);
    }

    public void payed() {
        state.setPaid(this);
    }

    public void refunded() {
        state.setRefunded(this);
    }
}
