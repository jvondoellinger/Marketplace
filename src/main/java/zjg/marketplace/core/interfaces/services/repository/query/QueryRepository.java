package zjg.marketplace.core.interfaces.services.repository.query;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QueryRepository<TEntity> {
    Mono<TEntity> findById(String id);
    Flux<TEntity> findWithPagination(Long offset, Integer limit);
}