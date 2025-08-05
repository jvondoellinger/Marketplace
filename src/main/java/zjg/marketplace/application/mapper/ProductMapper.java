package zjg.marketplace.application.mapper;

import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.product.ProductBuilder;
import zjg.marketplace.core.factory.product.ProductFactory;

public class ProductMapper {
    public static Product map(ProductInput input) {
        return ProductFactory.factory(
                input.getTitle(),
                input.getDescription(),
                input.getAmount()
        );
    }
    public static Product unsafeMap(ProductInput input) {
        return ProductBuilder.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .amount(input.getAmount())
                .build();
    }
}
