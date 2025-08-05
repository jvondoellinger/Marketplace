package zjg.marketplace.core.strategy.impl.product.updater;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.exceptions.updater.product.SameDescriptionException;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.StringUtils;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

public class DescriptionUpdater implements UpdateStrategy<Product> {
    private static final String SAME_DESCRIPTION_MESSAGE = "Cannot be sended the same description";
    @Override
    public void applyUpdate(Product target, Product source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var description = source.getDescription();
        if(StringUtils.blankOrNull(description)) return;
        if(target.getDescription().equals(source.getDescription())) throw new SameDescriptionException(SAME_DESCRIPTION_MESSAGE);
        target.toBuilder().description(description);
    }
}
