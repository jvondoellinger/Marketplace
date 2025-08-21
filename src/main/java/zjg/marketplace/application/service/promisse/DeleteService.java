package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Mono;

public interface DeleteService<TEntity> {
    Mono<Void> delete(TEntity entity);
    Mono<Void> deleteById(String id);
}
