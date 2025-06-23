package zjg.marketplace.core.interfaces.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.base.BaseEntity;

public interface IRepository<TEntity extends BaseEntity> {
    Mono<TEntity> insert(TEntity entity);
    /// * ATTENTION: All fields do updated, except CreatedAt!
    /// * OBS: The update operation use the id present in entity!
    Mono<TEntity> update(TEntity updated);
    Mono<Void> delete(String id);
    Mono<TEntity> findById(String id);
    Flux<TEntity> findWithPagination(Long offset, Integer limit);
}
