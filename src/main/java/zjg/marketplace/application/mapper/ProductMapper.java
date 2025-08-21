package zjg.marketplace.application.mapper;

import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.entity.ProductBuilder;
import zjg.marketplace.core.product.entity.ProductFactory;

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
