package zjg.marketplace.core.strategy.interfaces;

public interface IValidator<TEntity> {
    void validate(TEntity entity);
}
