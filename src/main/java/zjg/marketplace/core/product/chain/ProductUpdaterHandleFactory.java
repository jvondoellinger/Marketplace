package zjg.marketplace.core.product.chain;

import zjg.marketplace.core.chain.impl.updater.EntityUpdaterHandler;
import zjg.marketplace.core.chain.interfaces.DualHandler;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.strategies.updater.AmountUpdater;
import zjg.marketplace.core.product.strategies.updater.DescriptionUpdater;
import zjg.marketplace.core.product.strategies.updater.PathsUpdater;
import zjg.marketplace.core.product.strategies.updater.TitleUpdater;

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
