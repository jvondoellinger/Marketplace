package zjg.marketplace.core.factory.chain.updater;

import zjg.marketplace.core.chain.impl.updater.EntityUpdaterHandler;
import zjg.marketplace.core.chain.interfaces.DualHandler;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.impl.product.updater.AmountUpdater;
import zjg.marketplace.core.strategy.impl.product.updater.DescriptionUpdater;
import zjg.marketplace.core.strategy.impl.product.updater.PathsUpdater;
import zjg.marketplace.core.strategy.impl.product.updater.TitleUpdater;

public class ProductUpdaterHandleFactory {

    private ProductUpdaterHandleFactory() {}

    public static DualHandler<Product> factory() {
        var chain = new EntityUpdaterHandler<Product>();
        chain.setNext(new AmountUpdater());
        chain.setNext(new DescriptionUpdater());
        chain.setNext(new PathsUpdater());
        chain.setNext(new TitleUpdater());
        return chain;
    }
}
