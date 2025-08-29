package zjg.marketplace.core.order.strategies.updater;

import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.BigDecimalUtils;
import zjg.marketplace.core.strategy.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class OrderAmountUpdater implements UpdateStrategy<Order> {
    @Override
    public void applyUpdate(Order target, Order source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var amount = source.getAmount();
        if(Objects.isNull(amount)) return;
        if(!BigDecimalUtils.isGreaterThanOne(amount)) return;
        target.toBuilder().amount(amount);
    }
}
