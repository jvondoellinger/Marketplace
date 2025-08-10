package zjg.marketplace.core.interfaces.services.repository.query;

import reactor.core.publisher.Flux;
import zjg.marketplace.core.entity.base.BaseEntity;

public interface QueryByUserIdRepository<TEntity extends BaseEntity> {
    Flux<TEntity> findByUserId(String userId);
}
