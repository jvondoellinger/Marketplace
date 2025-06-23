package zjg.marketplace.core.strategy.impl.product;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public class TitleValidator implements IValidator<Product> {
    final static String DEFAULT_MESSAGE = "You provided a invalid username! Please, send another username.";
    final static String SHORT_TITLE = "You provided a short username! Please, send a longer username.";
    final static String LONGER_TITLE = "You provided a short username! Please, send a longer username.";

    @Override
    public void validate(Product product) {
        var s = product.getTitle();
        var n = s.length();
        Objects.requireNonNull(s, DEFAULT_MESSAGE);
        if(s.isBlank()) throw new IllegalArgumentException(DEFAULT_MESSAGE);
        if(n < 5) throw new IllegalArgumentException(SHORT_TITLE);
        if(n > 255) throw new IllegalArgumentException(LONGER_TITLE);
    }
}
