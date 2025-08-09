package zjg.marketplace.core.factory.product;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.valueObjects.path.ImagePath;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.product.ProductBuilder;
import zjg.marketplace.core.factory.chain.validators.ProductValidatorHandlerFactory;
import zjg.marketplace.core.valueObjects.path.ImagePaths;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Não me parece um código limpo! Depois vou melhorar isso!

@BadCode
public class ProductFactory {
    public static Product factory(String title, String description, BigDecimal amount, ImagePaths paths) {
        var validator = ProductValidatorHandlerFactory.factory();
        var product = ProductBuilder.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .path(paths)
                .build();
        validator.handle(product);
        return product;
    }

    public static Product factory(String title, String description, BigDecimal amount) {
        var validator = ProductValidatorHandlerFactory.factory();
        var product = ProductBuilder.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .build();
        validator.handle(product);
        return product;
    }
}
