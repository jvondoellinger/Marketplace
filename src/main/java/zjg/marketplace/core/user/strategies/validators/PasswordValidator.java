package zjg.marketplace.core.user.strategies.validators;

import zjg.marketplace.core.user.strategies.validators.exceptions.password.ShortPasswordException;
import zjg.marketplace.core.user.strategies.validators.exceptions.password.WeakPasswordException;
import zjg.marketplace.core.strategy.interfaces.Validator;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.utils.StringUtils;

public class PasswordValidator implements Validator<User> {
    private final static String DEFAULT_MESSAGE = "You provided a invalid password! Please, send another password.";
    private final static String SHORT_PASSWORD = "You provided a short password! Please, send a longer password.";
    private final static String INSECURE_PASSWORD = "You provided a insecure password! Please, send a longer password.";
    private final static String BLANK_PASSWORD = "You provided a blank password! Please, send a valid password.";
    private final static int MINIMAL_LENGTH = 8;
    @Override
    public void validate(User user) {
        var s = user.getPassword();
        if(StringUtils.blankOrNull(s)) throw new ShortPasswordException(BLANK_PASSWORD);
        if(s.length() < MINIMAL_LENGTH) throw new ShortPasswordException(SHORT_PASSWORD);
        if(!s.matches(".*[^a-zA-Z0-9 ].*")) throw new WeakPasswordException(INSECURE_PASSWORD);
    }
}
