package zjg.marketplace.core.entity;

import zjg.marketplace.anotation.BadCode;
import zjg.marketplace.core.interfaces.builder.Reconstructable;
import zjg.marketplace.core.interfaces.clone.IClonable;

import java.math.BigDecimal;
import java.util.Date;

public class Product extends BaseEntity implements Reconstructable<ProductBuilder>, IClonable<Product> {
    protected Product() {
        super();
    }

    private String title;
    private String description;
    private BigDecimal amount;
    private String imagePath = "empty.jpg"; // Default image

    // Getter
    public BigDecimal getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }
    public String getImagePath() {
        return imagePath;
    }
    // Setters_
    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setDescription(String description) {
        this.description = description;
    }
    protected void setTitle(String title) {
        this.title = title;
    }
    protected void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public ProductBuilder toBuilder() {
        return new ProductBuilder(this);
    }

    @BadCode
    public Product updateFrom(Product partialUser) {
        var amount = partialUser.getAmount();
        var description = partialUser.getDescription();
        var title = partialUser.getTitle();
        var path = partialUser.getImagePath();
        this.setTitle(title == null ? this.getTitle() : title);
        this.setDescription(description == null ? this.getDescription() : description);
        this.setAmount(amount == null ? this.getAmount() : amount);
        this.setImagePath(path == null ? getImagePath() : path);
        this.setUpdatedAt(new Date());
        return this;
    }

    @Override
    public Product clone() {
        return new ProductBuilder()
                .id(getId())
                .title(getTitle())
                .description(getDescription())
                .amount(getAmount())
                .imagePath(getImagePath())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}
