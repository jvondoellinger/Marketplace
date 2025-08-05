package zjg.marketplace.core.factory.chain.updater;

import zjg.marketplace.core.chain.impl.updater.EntityUpdaterHandler;
import zjg.marketplace.core.chain.interfaces.IDualHandler;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.impl.order.updater.OrderAmountUpdater;
import zjg.marketplace.core.strategy.impl.order.updater.OrderStatusUpdater;
import zjg.marketplace.core.strategy.impl.order.updater.ProductsIdUpdater;
import zjg.marketplace.core.strategy.impl.product.updater.AmountUpdater;
import zjg.marketplace.core.strategy.impl.product.updater.DescriptionUpdater;
import zjg.marketplace.core.strategy.impl.product.updater.PathsUpdater;
import zjg.marketplace.core.strategy.impl.product.updater.TitleUpdater;

public class ProductUpdaterHandleFactory {

    private ProductUpdaterHandleFactory() {}

    public static IDualHandler<Product> factory() {
        var chain = new EntityUpdaterHandler<Product>();
        chain.setNext(new AmountUpdater());
        chain.setNext(new DescriptionUpdater());
        chain.setNext(new PathsUpdater());
        chain.setNext(new TitleUpdater());
        return chain;
    }
}
