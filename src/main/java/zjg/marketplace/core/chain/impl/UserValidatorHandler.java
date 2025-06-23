package zjg.marketplace.core.chain.impl;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.chain.abstractions.AbstractValidationHandler;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.interfaces.IValidator;

@BadCode
public class UserValidatorHandler extends AbstractValidationHandler<User> {
    public UserValidatorHandler() {
        super();
    }

    public UserValidatorHandler(IValidator<User> validationHandler) {
        super(validationHandler);
    }

    @Override
    protected AbstractValidationHandler<User> self() {
        return this;
    }

    @Override
    protected AbstractValidationHandler<User> self(IValidator<User> validatorHandler) {
        return new UserValidatorHandler(validatorHandler);
    }
}
