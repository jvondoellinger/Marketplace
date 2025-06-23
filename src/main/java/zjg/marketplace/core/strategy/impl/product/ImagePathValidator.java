package zjg.marketplace.core.strategy.impl.product;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public class ImagePathValidator implements IValidator<Product> {
    final static String DEFAULT_MESSAGE = "You provided an invalid path name! Please, send another path name.";
    final static String NEED_EXTENSION = "You provided an invalid path name. Please send another one that includes the extension.";

    @Override
    public void validate(Product product) {
        var s = product.getPaths();
        Objects.requireNonNull(s, DEFAULT_MESSAGE);
        if(s.isEmpty()) throw new IllegalArgumentException(DEFAULT_MESSAGE);
        if(!s.contains(".")) throw new IllegalArgumentException(NEED_EXTENSION);
    }
}
