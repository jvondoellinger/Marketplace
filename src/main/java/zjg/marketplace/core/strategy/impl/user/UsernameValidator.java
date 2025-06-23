package zjg.marketplace.core.strategy.impl.user;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public class UsernameValidator implements IValidator<User> {
    final static String DEFAULT_MESSAGE = "You provided a invalid username! Please, send another username.";
    final static String SHORT_USERNAME = "You provided a short username! Please, send a longer username.";
    final static String BIGGER_MESSAGE = "You provided a bigger username! Please, send shorter username.";

    @Override
    public void validate(User user) {
        var s = user.getUsername();
        Objects.requireNonNull(s, DEFAULT_MESSAGE);
        var countedChars = s.length();
        if(s.isBlank()) throw new IllegalArgumentException(DEFAULT_MESSAGE);
        if(countedChars < 3) throw new IllegalArgumentException(SHORT_USERNAME);
        if(countedChars > 100) throw new IllegalArgumentException(BIGGER_MESSAGE);
    }
}
