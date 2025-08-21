package zjg.marketplace.core.product.strategies.updater;

import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.strategies.updater.exceptions.SameTitleException;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.StringUtils;
import zjg.marketplace.core.strategy.utils.UpdaterStrategyUtils;

public class TitleUpdater implements UpdateStrategy<Product> {
    private static final String SAME_TITLE_MESSAGE = "Cannot be sended the same description";

    @Override
    public void applyUpdate(Product target, Product source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var title = source.getTitle();
        if(StringUtils.blankOrNull(title)) return;
        if(target.getTitle().equals(source.getTitle())) throw new SameTitleException(SAME_TITLE_MESSAGE); // * Or return?
        target.toBuilder().title(title);
    }
}
