package zjg.marketplace.core.factory.chain.updater;

import zjg.marketplace.core.chain.impl.updater.EntityUpdaterHandler;
import zjg.marketplace.core.chain.interfaces.DualHandler;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.strategy.impl.order.updater.OrderAmountUpdater;
import zjg.marketplace.core.strategy.impl.order.updater.OrderStatusUpdater;
import zjg.marketplace.core.strategy.impl.order.updater.ProductsIdUpdater;

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
