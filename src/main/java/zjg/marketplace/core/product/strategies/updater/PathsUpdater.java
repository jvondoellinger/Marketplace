package zjg.marketplace.core.product.strategies.updater;

import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.strategy.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class PathsUpdater implements UpdateStrategy<Product> {
    @Override
    public void applyUpdate(Product target, Product source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        var paths = source.getPaths();
        if (Objects.isNull(paths)) return;
        if (Objects.isNull(paths.getPaths())) return;
        if (paths.getPaths().isEmpty()) return;
        target.toBuilder().path(paths);
    }
}
