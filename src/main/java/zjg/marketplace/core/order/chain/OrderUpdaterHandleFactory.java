package zjg.marketplace.core.order.chain;

import zjg.marketplace.core.chain.impl.updater.EntityUpdaterHandler;
import zjg.marketplace.core.chain.interfaces.DualHandler;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.strategies.updater.OrderAmountUpdater;
import zjg.marketplace.core.order.strategies.updater.OrderStatusUpdater;
import zjg.marketplace.core.order.strategies.updater.ProductsIdUpdater;

public class OrderUpdaterHandleFactory {
    private OrderUpdaterHandleFactory() {}

    public static DualHandler<Order> factory() {
        var chain = new EntityUpdaterHandler<Order>();
        chain.setNext(new OrderAmountUpdater());
        chain.setNext(new OrderStatusUpdater());
        chain.setNext(new ProductsIdUpdater());
        return chain;
    }
}
