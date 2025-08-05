package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFindService<T> {
    Flux<T> get(Long offset, Integer limit);
    Mono<T> findById(String id);
    Mono<T> findByIdNoCache(String id);
}
