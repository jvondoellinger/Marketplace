package zjg.marketplace.core.strategy.impl.user.validators;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.exceptions.validations.username.BlankUsernameException;
import zjg.marketplace.core.exceptions.validations.username.UsernameTooShortException;
import zjg.marketplace.core.strategy.interfaces.Validator;

public class UsernameValidator implements Validator<User> {
    final static String DEFAULT_MESSAGE = "You provided a invalid username! Please, send another username.";
    final static String SHORT_USERNAME = "You provided a short username! Please, send a longer username.";
    final static String BIGGER_MESSAGE = "You provided a bigger username! Please, send shorter username.";

    @Override
    public void validate(User user) {
        var s = user.getUsername();
        var countedChars = s.length();
        if(s.isBlank()) throw new BlankUsernameException(DEFAULT_MESSAGE);
        if(countedChars < 3) throw new UsernameTooShortException(SHORT_USERNAME);
        if(countedChars > 100) throw new UsernameTooShortException(BIGGER_MESSAGE);
    }
}
