package zjg.marketplace.repository.custom;

import reactor.core.publisher.Flux;

public interface BasicFindRepository<T> {
    Flux<T> findWithPagination(Long offset, int limit);
}
