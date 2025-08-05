package zjg.marketplace.core.factory.order;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.factory.chain.validators.OrderValidatorHandleFactory;

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
