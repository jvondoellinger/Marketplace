package zjg.marketplace.core.strategy.impl.user.updater;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.exceptions.updater.user.SameUsernameException;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class UsernameUpdater implements UpdateStrategy<User> {
    @Override
    public void applyUpdate(User target, User source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        if (Objects.isNull(source.getUsername())) return;
        if (source.getUsername().isBlank()) return;
        if (target.getUsername().equals(source.getUsername())) throw new SameUsernameException();
        target.toBuilder().username(source.getUsername());
    }
}
