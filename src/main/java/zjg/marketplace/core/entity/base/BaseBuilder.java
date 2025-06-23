package zjg.marketplace.core.entity.base;

import java.util.Date;

public abstract class BaseBuilder<TEntity extends BaseEntity, TBuilder> {
    protected final TEntity entity;
    protected BaseBuilder(TEntity entity) {
        this.entity = entity;
    }

    protected abstract TBuilder self();

    public TBuilder id(String identifier) {
        entity.setId(identifier);
        return self();
    }
    public TBuilder createdAt(Date createdAt) {
        entity.setCreatedAt(createdAt);
        return self();
    }
    public TBuilder updatedAt(Date updatedAt) {
        entity.setUpdatedAt(updatedAt);
        return self();
    }

    public TEntity build() {
        return entity;
    }
}
