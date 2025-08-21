package zjg.marketplace.core.order.strategies.updater;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.rules.OrderRules;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.strategy.utils.UpdaterStrategyUtils;

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
