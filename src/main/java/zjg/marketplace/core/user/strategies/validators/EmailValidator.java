package zjg.marketplace.core.user.strategies.validators;

import zjg.marketplace.core.user.strategies.validators.exceptions.email.EmailFormatException;
import zjg.marketplace.core.strategy.interfaces.Validator;
import zjg.marketplace.core.user.entity.User;

// Se mudar a tipagem de String para User, fica tranquilo para criar uma cadeia de validações!\
public class EmailValidator implements Validator<User> {
    private final static String DEFAULT_MESSAGE = "You provided a invalid email! Please, send a valid email.";
    @Override
    public void validate(User user) {
        var s = user.getEmail();
        var indexA = s.indexOf("@");
        if(indexA == -1) throw new EmailFormatException(DEFAULT_MESSAGE);
        if(!s.contains(".")) throw new EmailFormatException(DEFAULT_MESSAGE);
        if(s.substring(0, indexA).length() <= 1) throw new EmailFormatException(DEFAULT_MESSAGE);
    }
}
