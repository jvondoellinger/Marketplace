package zjg.marketplace.core.chain.abstractions;

import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public abstract class AbstractValidationHandler<T> {
    protected AbstractValidationHandler next;
    protected IValidator<T> validator;

    protected AbstractValidationHandler() {}

    protected AbstractValidationHandler(IValidator<T> validator) {
        this.validator = validator;
    }

    // void setNext(T next);
    public void handle(T input) {
        Objects.requireNonNull(input, "The input can't be null!");
        if(next != null) next.handle(input);
        if(validator != null) validator.validate(input);
    }
    public void setNext(IValidator<T> validator) {
        Objects.requireNonNull(validator, "The next validator can't be null!");
        if(next == null) {
            this.next = self(validator);
        } else {
            this.next.setNext(validator);
        }
    }

    protected abstract AbstractValidationHandler<T> self();
    protected abstract AbstractValidationHandler<T> self(IValidator<T> validatorHandler);
}
