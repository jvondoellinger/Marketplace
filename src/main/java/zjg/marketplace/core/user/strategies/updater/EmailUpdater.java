package zjg.marketplace.core.user.strategies.updater;

import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.strategies.updater.exceptions.SameEmailException;
import zjg.marketplace.core.utils.StringUtils;
import zjg.marketplace.core.strategy.utils.UpdaterStrategyUtils;

public class EmailUpdater implements UpdateStrategy<User> {
    private static final String SAME_EMAIL_MESSAGE = "";
    @Override
    public void applyUpdate(User target, User source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        if(StringUtils.blankOrNull(source.getEmail())) return;
        if(target.getEmail().equals(source.getEmail())) throw new SameEmailException(SAME_EMAIL_MESSAGE);
        target.toBuilder().email(source.getEmail());
    }


}
