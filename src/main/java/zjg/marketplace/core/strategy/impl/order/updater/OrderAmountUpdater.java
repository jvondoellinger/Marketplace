package zjg.marketplace.core.strategy.impl.order.updater;

import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.rules.OrderRules;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.BigDecimalUtils;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class OrderAmountUpdater implements UpdateStrategy<Order> {
    @Override
    public void applyUpdate(Order target, Order source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        OrderRules.throwIfCannotUpdate(target);
        var amount = source.getAmount();
        if(Objects.isNull(amount)) return;
        if(!BigDecimalUtils.isGreaterThanOne(amount)) return;
        target.toBuilder().amount(amount);
    }
}
