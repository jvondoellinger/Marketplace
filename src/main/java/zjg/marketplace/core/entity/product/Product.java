package zjg.marketplace.core.entity.product;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.valueObjects.path.ImagePath;
import zjg.marketplace.core.interfaces.compositions.builder.Reconstructable;
import zjg.marketplace.core.interfaces.compositions.clone.IClonable;
import zjg.marketplace.core.strategy.interfaces.common.IAmountGetterStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Product extends BaseEntity implements Reconstructable<ProductBuilder>, IClonable<Product>, IAmountGetterStrategy {
    // Constructors --------------------------------------------
    protected Product() {
        super();
        paths = new CopyOnWriteArrayList<>();
    }

    // Properties ----------------------------------------------
    private String title;
    private String description;
    private BigDecimal amount;
    private List<ImagePath> paths;

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
    public List<ImagePath> getPaths() {
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
    @BadCode
    public void setPaths(List<ImagePath> paths) {
        this.paths = paths;
    } // Alterar

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

    // Custom ----------------------------------------------
    public synchronized void addPath(ImagePath path) {
        this.paths.add(path);
    }
    public synchronized void removePath(ImagePath path) {
        this.paths.remove(path);
    }

    public synchronized void addBatchPath(List<ImagePath> path) {
        this.paths.addAll(path);
    }
    public synchronized void removeBatchPath(List<ImagePath> path) {
        this.paths.removeAll(path);
    }

    @Override
    public String toString() {
        return STR."Product{title='\{title}', description='\{description}', amount=\{amount}, paths=\{paths}}";
    }
}
