package zjg.marketplace.core.strategy.interfaces;

import reactor.core.publisher.Mono;

public interface IValidator<TEntity> {
    void validate(TEntity entity);
}
