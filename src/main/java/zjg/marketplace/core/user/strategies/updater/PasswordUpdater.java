package zjg.marketplace.core.user.strategies.updater;

import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.strategies.updater.exceptions.SamePasswordException;
import zjg.marketplace.core.strategy.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class PasswordUpdater implements UpdateStrategy<User> {
    @Override
    public void applyUpdate(User target, User source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        if (Objects.isNull(source.getPassword())) return;
        if (source.getPassword().isBlank()) return;
        if (source.getPassword().equals(source.getPassword())) throw new SamePasswordException();
        target.toBuilder().username(source.getUsername());
    }
}
