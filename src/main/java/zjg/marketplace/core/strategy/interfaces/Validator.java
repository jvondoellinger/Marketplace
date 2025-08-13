package zjg.marketplace.core.strategy.interfaces;

public interface Validator<TEntity> {
    void validate(TEntity entity);
}
