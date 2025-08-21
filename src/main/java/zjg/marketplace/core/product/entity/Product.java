package zjg.marketplace.core.product.entity;

import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.image.valueObj.path.ImagePathList;
import zjg.marketplace.core.interfaces.compositions.builder.Reconstructable;
import zjg.marketplace.core.interfaces.compositions.clone.IClonable;
import zjg.marketplace.core.strategy.interfaces.common.IAmountGetterStrategy;

import java.math.BigDecimal;

public class Product extends BaseEntity implements Reconstructable<ProductBuilder>, IClonable<Product>, IAmountGetterStrategy {
    // * Constructors --------------------------------------------
    protected Product() {
        super();
    }

    // * Properties ----------------------------------------------
    private String title;
    private String description;
    private BigDecimal amount;
    private ImagePathList paths;

    // * Getter -------------------------------------------------
    public BigDecimal getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }

    public ImagePathList getPaths() {
        return paths;
    }

    // * Setters ------------------------------------------------
    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setDescription(String description) {
        this.description = description;
    }
    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setPaths(ImagePathList paths) {
        this.paths = paths;
    }

    // * Overrides  ----------------------------------------------
    @Override
    public ProductBuilder toBuilder() {
        return new ProductBuilder(this);
    }

    @Override
    public Product clone() {
        return new ProductBuilder()
                .id(getId())
                .title(getTitle())
                .description(getDescription())
                .amount(getAmount())
                .path(getPaths())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

    @Override
    public String toString() {
        return STR."Product{title='\{title}', description='\{description}', amount=\{amount}, paths=\{paths}}";
    }
}
