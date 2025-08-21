package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Mono;

public interface UpdateService<TEntity, TInput> {
    Mono<TEntity> update(TInput input, String id);
}
