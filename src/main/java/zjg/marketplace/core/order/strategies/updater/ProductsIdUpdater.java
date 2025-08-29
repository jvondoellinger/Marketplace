package zjg.marketplace.core.order.strategies.updater;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.strategy.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class ProductsIdUpdater implements UpdateStrategy<Order> {
    @Override
    public void applyUpdate(Order target, Order source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var ids = source.getProductsId();
        if(Objects.isNull(ids)) return;
        if(ids.isEmpty()) return;
        target.toBuilder().productsId(ids);
    }
}
