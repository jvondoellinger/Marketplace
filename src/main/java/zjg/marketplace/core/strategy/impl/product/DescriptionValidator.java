package zjg.marketplace.core.strategy.impl.product;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public class DescriptionValidator implements IValidator<Product> {
    final static String DEFAULT_MESSAGE = "You provided a invalid description! Please, send another description.";
    final static String SHORT_DESCRIPTION = "You provided a short description! Please, send a longer description.";
    final static String LONGER_DESCRIPTION = "You provided a short description! Please, send a longer description.";

    @Override
    public void validate(Product product) {
        var s = product.getDescription();
        var n = s.length();
        Objects.requireNonNull(s, DEFAULT_MESSAGE);
        if(s.isBlank()) throw new IllegalArgumentException(DEFAULT_MESSAGE);
        if(n < 5) throw new IllegalArgumentException(SHORT_DESCRIPTION);
        if(n > 5000) throw new IllegalArgumentException(LONGER_DESCRIPTION);
    }
}
