package zjg.marketplace.core.entity.product;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.field.path.CustomPath;
import zjg.marketplace.core.interfaces.builder.Reconstructable;
import zjg.marketplace.core.interfaces.clone.IClonable;
import zjg.marketplace.core.interfaces.helper.ISelfUpdatable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Product extends BaseEntity implements Reconstructable<ProductBuilder>, IClonable<Product>, ISelfUpdatable<Product> {
    // Constructors --------------------------------------------
    protected Product() {
        super();
    }

    // Properties ----------------------------------------------
    private String title;
    private String description;
    private BigDecimal amount;
    private List<CustomPath> paths; // Default image

    // Getter -------------------------------------------------
    public BigDecimal getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }
    public List<CustomPath> getPaths() {
        return paths;
    }

    // Setters ------------------------------------------------
    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    protected void setDescription(String description) {
        this.description = description;
    }
    protected void setTitle(String title) {
        this.title = title;
    }
    protected void addImagePath(CustomPath imagePath) {
        this.paths.add(imagePath);
    }
    protected void setPaths(List<CustomPath> paths) {
        this.paths = paths;
    }

    // Overrides  ----------------------------------------------
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
                .imagePath(getPaths())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

    @Override
    public Product selfUpdate(Product partial) {
        var amount = partial.getAmount();
        var description = partial.getDescription();
        var title = partial.getTitle();
        var path = partial.getPaths();

        this.setTitle(title == null ? this.getTitle() : title);
        this.setDescription(description == null ? this.getDescription() : description);
        this.setAmount(amount == null ? this.getAmount() : amount);
        this.setPaths(path == null ? getPaths() : path);
        this.setUpdatedAt(new Date());
        return this;
    }

    // Custom ----------------------------------------------
    public void addPath(CustomPath path) {
        this.paths.add(path);
    }
    public void removePath(CustomPath path) {
        this.paths.remove(path);
    }

    public void addBatchPath(List<CustomPath> path) {
        this.paths.addAll(path);
    }
    public void removeBatchPath(List<CustomPath> path) {
        this.paths.removeAll(path);
    }

}
