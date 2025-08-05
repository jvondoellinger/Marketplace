package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBasicService<TEntity, TInput> {
    Mono<TEntity> create(TInput input);
    Mono<TEntity> update(TInput partialEntity, String identifier);
    Mono<Void> delete(String identifier);
    Mono<TEntity> findById(String identifier);
    Mono<List<TEntity>> get(Long offset, Integer max);
}
