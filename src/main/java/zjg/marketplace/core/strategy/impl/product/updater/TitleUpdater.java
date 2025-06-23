package zjg.marketplace.core.strategy.impl.product.updater;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.exceptions.updater.product.SameTitleException;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.StringUtils;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

public class TitleUpdater implements UpdateStrategy<Product> {
    @Override
    public void applyUpdate(Product target, Product source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var title = source.getTitle();
        if(StringUtils.blankOrNull(title)) return;
        if(target.getTitle().equals(source.getTitle())) throw new SameTitleException();
        target.toBuilder().title(title);
    }
}
