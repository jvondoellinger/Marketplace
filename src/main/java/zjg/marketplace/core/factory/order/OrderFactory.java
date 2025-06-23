package zjg.marketplace.core.factory.order;

import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.user.User;

public class OrderFactory {
    public static Order factory() {
        return new Order();
    }
    public static Order factory(User buyer) {
        return new Order(buyer);
    }
}
