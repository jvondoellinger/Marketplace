package zjg.marketplace.core.chain.impl.validators;

import zjg.marketplace.core.chain.interfaces.Handler;
import zjg.marketplace.core.chain.interfaces.NextHandler;
import zjg.marketplace.core.strategy.interfaces.Validator;
import java.util.Objects;

public class ValidationHandler<T> implements Handler<T>, NextHandler<Validator<T>> {
    protected ValidationHandler<T> next;
    protected Validator<T> validator;
    public ValidationHandler() {}

    private ValidationHandler(Validator<T> validator) {
        this.validator = validator;
    }

    @Override
    public void handle(T t) {
        Objects.requireNonNull(t, "The input t1 can't be null!");
        if(!Objects.isNull(validator)) validator.validate(t);
        if(!Objects.isNull(next)) next.handle(t);
    }
    // tentar abstrair isso com o INextHandler
    @Override
    public void setNext(Validator<T> validator) {
        if (next == null) {
            this.next = new ValidationHandler<>(validator);
        } else {
            this.next.setNext(validator);
        }
    }
}
