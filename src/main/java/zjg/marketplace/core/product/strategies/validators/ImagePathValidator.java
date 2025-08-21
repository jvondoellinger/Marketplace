package zjg.marketplace.core.product.strategies.validators;

import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.strategy.interfaces.Validator;

import java.util.Objects;

public class ImagePathValidator implements Validator<Product> {
    private final static String DEFAULT_MESSAGE = "You provided an invalid path name! Please, send another path name.";
    private final static String NEED_EXTENSION = "You provided an invalid path name. Please send another one that includes the extension.";

    @Override
    public void validate(Product product) {
        var paths = product.getPaths();
        if (Objects.isNull(paths)) return;
        if (Objects.isNull(paths.getAll())) return;
        if (paths.getAll().isEmpty()) return;
    }
}
