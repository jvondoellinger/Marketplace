package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Mono;

public interface CreateService<TEntity, TInput> {
    Mono<TEntity> create(TInput input);
}
