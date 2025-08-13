package zjg.marketplace.core.strategy.impl.order.updater;

import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.rules.OrderRules;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class ProductsIdUpdater implements UpdateStrategy<Order> {
    @Override
    public void applyUpdate(Order target, Order source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        OrderRules.throwIfCannotUpdate(target);
        var ids = source.getProductsId();
        if(Objects.isNull(ids)) return;
        if(ids.isEmpty()) return;
        target.toBuilder().productsId(ids);
    }
}
