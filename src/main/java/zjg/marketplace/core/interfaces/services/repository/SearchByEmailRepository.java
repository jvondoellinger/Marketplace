package zjg.marketplace.core.interfaces.services.repository;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.base.BaseEntity;

public interface SearchByEmailRepository<TEntity extends BaseEntity> {
    Mono<TEntity> find(String email);
}
