package zjg.marketplace.core.strategy.impl.product;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.math.BigDecimal;
import java.util.Objects;

public class AmountValidator implements IValidator<Product> {
    final static String DEFAULT_MESSAGE = "You provided a invalid username! Please, send another username.";
    final static String SMALLER_AMOUNT = "The amount you provided is too small. Please send a larger one.";

    @Override
    public void validate(Product product) {
        var a = product.getAmount();
        Objects.requireNonNull(a, DEFAULT_MESSAGE);
        if(a.compareTo(new BigDecimal(1)) < 0) throw new IllegalArgumentException(SMALLER_AMOUNT);
    }
}
