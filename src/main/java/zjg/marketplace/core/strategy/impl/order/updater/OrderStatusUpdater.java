package zjg.marketplace.core.strategy.impl.order.updater;

import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.rules.OrderRules;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class OrderStatusUpdater implements UpdateStrategy<Order> {
    @Override
    public void applyUpdate(Order target, Order source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        OrderRules.throwIfCannotUpdate(target);
        var status = source.getStatus();
        if(Objects.isNull(status)) return;
        target.toBuilder().status(status);
    }
}
