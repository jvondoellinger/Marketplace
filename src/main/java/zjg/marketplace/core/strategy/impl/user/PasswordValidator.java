package zjg.marketplace.core.strategy.impl.user;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.interfaces.IValidator;
import zjg.marketplace.core.utils.ThrowUtils;

import java.util.Objects;

public class PasswordValidator implements IValidator<User> {
    final static String DEFAULT_MESSAGE = "You provided a invalid password! Please, send another password.";
    final static String SHORT_PASSWORD = "You provided a short password! Please, send a longer password.";
    final static String INSECURE_PASSWORD = "You provided a insecure password! Please, send a longer password.";

    @Override
    public void validate(User user) {
        var s = user.getPassword();
        Objects.requireNonNull(s, DEFAULT_MESSAGE);

        ThrowUtils.throwIfTrue(s.isBlank(), DEFAULT_MESSAGE, IllegalArgumentException.class);
        ThrowUtils.throwIfTrue(s.length() < 8, SHORT_PASSWORD, IllegalArgumentException.class);
        ThrowUtils.throwIfTrue(!s.matches(".*[^a-zA-Z0-9 ].*"), INSECURE_PASSWORD, IllegalArgumentException.class);
    }
}
