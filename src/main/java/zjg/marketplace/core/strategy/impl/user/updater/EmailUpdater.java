package zjg.marketplace.core.strategy.impl.user.updater;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.exceptions.updater.user.SameEmailException;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.StringUtils;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

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
