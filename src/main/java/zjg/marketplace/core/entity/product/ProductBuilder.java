package zjg.marketplace.core.entity.product;

import zjg.marketplace.core.entity.base.BaseBuilder;
import zjg.marketplace.core.valueObject.path.ImagePath;

import java.math.BigDecimal;
import java.util.List;

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
    public ProductBuilder imagePath(List<ImagePath> path) {
        entity.setPaths(path);
        return this;
    }

    @Override
    protected ProductBuilder self() {
        return this;
    }
}
