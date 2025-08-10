package zjg.marketplace.core.interfaces.services.repository.query;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.base.BaseEntity;

public interface QueryByEmailRepository<TEntity extends BaseEntity> {
    Mono<TEntity> find(String email);
}
