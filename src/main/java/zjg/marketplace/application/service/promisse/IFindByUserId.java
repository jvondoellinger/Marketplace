package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Flux;

public interface IFindByUserId<T> {
    Flux<T> findByUserId(String userId);
}
