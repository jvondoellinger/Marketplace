package zjg.marketplace.core.entity.product;

import zjg.marketplace.core.entity.base.BaseBuilder;
import zjg.marketplace.core.image.valueObj.path.ImagePathList;

import java.math.BigDecimal;

public class ProductBuilder extends BaseBuilder<Product, ProductBuilder> {
    protected ProductBuilder() {
        super(new Product());
    }
    protected ProductBuilder(Product product) {
        super(product);
    }
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public ProductBuilder title(String title) {
        entity.setTitle(title);
        return this;
    }
    public ProductBuilder description(String description) {
        entity.setDescription(description);
        return this;
    }
    public ProductBuilder amount(BigDecimal amount) {
        entity.setAmount(amount);
        return this;
    }

    public ProductBuilder path(ImagePathList paths) {
        entity.setPaths(paths);
        return this;
    }

    @Override
    protected ProductBuilder self() {
        return this;
    }
}
