package zjg.marketplace.core.factory.chain;

import org.springframework.stereotype.Service;
import zjg.marketplace.core.chain.impl.UserValidatorHandler;
import zjg.marketplace.core.strategy.impl.user.*;

@Service
public class UserValidatorHandlerFactory {
    public UserValidatorHandlerFactory() {}

    public static UserValidatorHandler factory() {
        var instance = new UserValidatorHandler();
        instance.setNext(new EmailValidator());
        instance.setNext(new PasswordValidator());
        instance.setNext(new UsernameValidator());
        instance.setNext(new BirthdayValidator());
        instance.setNext(new PhoneValidator());
        return instance;
    }
}
