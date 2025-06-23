package zjg.marketplace.core.strategy.impl.product.updater;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class PathsUpdater implements UpdateStrategy<Product> {
    @Override
    public void applyUpdate(Product target, Product source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var paths = source.getPaths();
        if(Objects.isNull(paths)) return;
        if(paths.isEmpty()) return;
        target.toBuilder().imagePath(paths);
    }
}
