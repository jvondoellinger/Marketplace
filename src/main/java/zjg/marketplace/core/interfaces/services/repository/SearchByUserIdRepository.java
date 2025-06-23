package zjg.marketplace.core.interfaces.services.repository;

import reactor.core.publisher.Flux;
import zjg.marketplace.core.entity.base.BaseEntity;

public interface SearchByUserIdRepository<TEntity extends BaseEntity> {
    Flux<TEntity> findByUserId(String userId);
}
