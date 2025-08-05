package zjg.marketplace.core.factory.chain.validators;

import zjg.marketplace.core.chain.impl.validators.ValidationHandler;
import zjg.marketplace.core.chain.interfaces.Handler;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.impl.user.validators.*;


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
