package zjg.marketplace.core.products.entity;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.product.ProductBuilder;
import zjg.marketplace.core.image.valueObj.path.ImagePathList;
import zjg.marketplace.core.products.chain.ProductValidatorHandlerFactory;

import java.math.BigDecimal;

// Não me parece um código limpo! Depois vou melhorar isso!

@BadCode
public class ProductFactory {
    public static Product factory(String title, String description, BigDecimal amount, ImagePathList paths) {
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
