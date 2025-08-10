package zjg.marketplace.core.interfaces.services.repository.command;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.base.BaseEntity;

public interface CommandRepository<TEntity extends BaseEntity> {
    Mono<TEntity> insert(TEntity entity);
    Mono<TEntity> update(TEntity updated);
    Mono<Void> delete(String id);
}
