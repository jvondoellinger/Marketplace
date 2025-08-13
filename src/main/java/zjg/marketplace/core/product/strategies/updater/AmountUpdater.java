package zjg.marketplace.core.strategy.impl.product.updater;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.BigDecimalUtils;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class AmountUpdater implements UpdateStrategy<Product> {
    @Override
    public void applyUpdate(Product target, Product source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var amount = source.getAmount();
        if(Objects.isNull(amount)) return;
        if(!BigDecimalUtils.isGreaterThanOne(amount)) return;
        target.toBuilder().amount(amount);
    }
}
