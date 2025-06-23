package zjg.marketplace.core.strategy.impl.user;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

// Se mudar a tipagem de String para User, fica tranquilo para criar uma cadeia de validações!\
public class EmailValidator implements IValidator<User> {
    final static String DEFAULT_MESSAGE = "You provided a invalid email! Please, send a valid email.";

    @Override
    public void validate(User user) {
        var s = user.getEmail();
        Objects.requireNonNull(s, DEFAULT_MESSAGE);
        if(s.length() < 5) throw new IllegalArgumentException(DEFAULT_MESSAGE);
        if(!s.contains("@")) throw new IllegalArgumentException(DEFAULT_MESSAGE);
        if(!s.contains(".")) throw new IllegalArgumentException(DEFAULT_MESSAGE);
    }
}
