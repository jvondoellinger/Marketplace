package zjg.marketplace.core.strategy.impl.user.updater;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.exceptions.updater.user.SamePhoneNumberException;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;
import zjg.marketplace.core.utils.UpdaterStrategyUtils;

import java.util.Objects;

public class PhoneUpdater implements UpdateStrategy<User> {
    @Override
    public void applyUpdate(User target, User source) {
        UpdaterStrategyUtils.throwIfNullSourceOrTarget(target, source);
        if(Objects.isNull(source.getPhone())) return;
        if(Objects.isNull(source.getPhone().getAreaCode())) return;;
        if(Objects.isNull(source.getPhone().getNumber())) return;;
        if(Objects.isNull(source.getPhone().getCountryCode())) return;;
        if(target.getPhone().equals(source.getPhone())) throw new SamePhoneNumberException();
        target.toBuilder().phone(source.getPhone());
    }
}
