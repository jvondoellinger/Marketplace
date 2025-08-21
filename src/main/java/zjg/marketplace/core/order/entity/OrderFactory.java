package zjg.marketplace.core.order.entity;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.order.chain.OrderValidatorHandleFactory;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.user.entity.User;

import java.util.List;
@BadCode
public class OrderFactory {
    public static Order factory(User buyer) {
        return new Order(buyer.getId());
    }

    public static Order factory(String buyerId, List<Product> products) {
        var handler = OrderValidatorHandleFactory.factory();
        var order = new Order(buyerId, products);
        handler.handle(order);
        return order;
    }
}
