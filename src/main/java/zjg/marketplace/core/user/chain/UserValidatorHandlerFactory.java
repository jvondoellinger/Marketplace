package zjg.marketplace.core.user.chain;

import zjg.marketplace.core.chain.impl.validators.ValidationHandler;
import zjg.marketplace.core.chain.interfaces.Handler;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.strategies.validators.*;


public class UserValidatorHandlerFactory {
    public UserValidatorHandlerFactory() {}

    public static Handler<User> factory() {
        var chain = new ValidationHandler<User>();
        chain.setNext(new EmailValidator());
        chain.setNext(new PasswordValidator());
        chain.setNext(new UsernameValidator());
        chain.setNext(new BirthdayValidator());
        chain.setNext(new PhoneValidator());
        chain.setNext(new CpfValidator());
        return chain;
    }
}
