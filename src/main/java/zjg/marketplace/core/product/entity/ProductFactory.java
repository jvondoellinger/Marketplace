package zjg.marketplace.core.product.entity;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.image.valueObj.path.ImagePathList;
import zjg.marketplace.core.product.chain.ProductValidatorHandlerFactory;

import java.math.BigDecimal;

// Não me parece um código limpo! Depois vou melhorar isso!

@BadCode
public class ProductFactory {
    public static zjg.marketplace.core.product.entity.Product factory(String title, String description, BigDecimal amount, ImagePathList paths) {
        var validator = ProductValidatorHandlerFactory.factory();
        var product = zjg.marketplace.core.product.entity.ProductBuilder.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .path(paths)
                .build();
        validator.handle(product);
        return product;
    }

    public static zjg.marketplace.core.product.entity.Product factory(String title, String description, BigDecimal amount) {
        var validator = ProductValidatorHandlerFactory.factory();
        var product = zjg.marketplace.core.product.entity.ProductBuilder.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .build();
        validator.handle(product);
        return product;
    }
}
