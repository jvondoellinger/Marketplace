package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindService<T> {
    Flux<T> get(long offset, int limit);
    Mono<T> findById(String id);
    Mono<T> findByIdNoCache(String id);
}
