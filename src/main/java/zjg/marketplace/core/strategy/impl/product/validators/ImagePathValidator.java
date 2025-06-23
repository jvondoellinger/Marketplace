package zjg.marketplace.core.strategy.impl.product.validators;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.exceptions.validations.product.path.BlankPathException;
import zjg.marketplace.core.exceptions.validations.product.path.InvalidExtensionException;
import zjg.marketplace.core.exceptions.validations.product.path.NullExtensionException;
import zjg.marketplace.core.exceptions.validations.product.path.NullPathException;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public class ImagePathValidator implements IValidator<Product> {
    private final static String DEFAULT_MESSAGE = "You provided an invalid path name! Please, send another path name.";
    private final static String NEED_EXTENSION = "You provided an invalid path name. Please send another one that includes the extension.";

    @Override
    public void validate(Product product) {
        var paths = product.getPaths();
        if(Objects.isNull(paths)) return;
        if(paths.isEmpty()) return;
        for(var path : paths) {
            if(Objects.isNull(path)) throw new NullPathException();
            if(Objects.isNull(path.getExtension())) throw new NullExtensionException();
        }
    }
}
