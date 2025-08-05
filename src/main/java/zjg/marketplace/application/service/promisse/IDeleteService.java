package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Mono;

public interface IDeleteService<TEntity> {
    Mono<Void> delete(TEntity entity);
    Mono<Void> deleteById(String id);
}
