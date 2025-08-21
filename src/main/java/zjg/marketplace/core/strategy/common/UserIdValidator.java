package zjg.marketplace.core.strategy.common;

import zjg.marketplace.core.strategy.interfaces.Validator;
import zjg.marketplace.core.strategy.interfaces.common.IUserIdGetterStrategy;
import java.util.Objects;

public class UserIdValidator<T extends IUserIdGetterStrategy> implements Validator<T> {
    @Override
    public void validate(IUserIdGetterStrategy userIdGetter) {
        var id = userIdGetter.getUserId();
        if(Objects.isNull(id)) throw new IllegalArgumentException("User Id cannot be null!");
        if(id.isBlank()) throw new IllegalArgumentException("User Id cannot be null!");
        if(id.length() < 5) throw new IllegalArgumentException("User Id isn't valid!");
    }
}
