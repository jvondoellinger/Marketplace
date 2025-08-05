package zjg.marketplace.core.factory.chain.updater;

import zjg.marketplace.core.chain.impl.updater.EntityUpdaterHandler;
import zjg.marketplace.core.chain.interfaces.IDualHandler;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.impl.user.updater.EmailUpdater;
import zjg.marketplace.core.strategy.impl.user.updater.PasswordUpdater;
import zjg.marketplace.core.strategy.impl.user.updater.PhoneUpdater;
import zjg.marketplace.core.strategy.impl.user.updater.UsernameUpdater;

public class UserUpdaterHandleFactory {
    private UserUpdaterHandleFactory() {}

    public static IDualHandler<User> factory() {
        var chain = new EntityUpdaterHandler<User>();
        chain.setNext(new UsernameUpdater());
        chain.setNext(new EmailUpdater());
        chain.setNext(new PasswordUpdater());
        chain.setNext(new PhoneUpdater());
        return chain;
    }
}
