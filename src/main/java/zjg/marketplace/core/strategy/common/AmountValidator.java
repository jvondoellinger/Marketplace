package zjg.marketplace.core.strategy.common;

import zjg.marketplace.core.strategy.interfaces.Validator;
import zjg.marketplace.core.strategy.interfaces.common.IAmountGetterStrategy;
import zjg.marketplace.core.utils.BigDecimalUtils;

import java.util.Objects;

public class AmountValidator<T extends IAmountGetterStrategy> implements Validator<T> {
    final static String DEFAULT_MESSAGE = "You provided a invalid username! Please, send another username.";
    final static String SMALLER_AMOUNT = "The amount you provided is too small. Please send a larger one.";

    @Override
    public void validate(T product) {
        Objects.requireNonNull(product, DEFAULT_MESSAGE);
        var amount = product.getAmount();
        Objects.requireNonNull(amount, DEFAULT_MESSAGE);
        if(!BigDecimalUtils.isGreaterThanOne(amount)) throw new IllegalArgumentException(SMALLER_AMOUNT);
    }
}
