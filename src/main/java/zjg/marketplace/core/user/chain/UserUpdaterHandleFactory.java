package zjg.marketplace.core.user.chain;

import zjg.marketplace.core.chain.impl.updater.EntityUpdaterHandler;
import zjg.marketplace.core.chain.interfaces.DualHandler;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.strategies.updater.EmailUpdater;
import zjg.marketplace.core.user.strategies.updater.PasswordUpdater;
import zjg.marketplace.core.user.strategies.updater.PhoneUpdater;
import zjg.marketplace.core.user.strategies.updater.UsernameUpdater;

public class UserUpdaterHandleFactory {
    private UserUpdaterHandleFactory() {}

    public static DualHandler<User> factory() {
        var chain = new EntityUpdaterHandler<User>();
        chain.setNext(new UsernameUpdater());
        chain.setNext(new EmailUpdater());
        chain.setNext(new PhoneUpdater());
        chain.setNext(new PasswordUpdater());
        return chain;
    }
}
