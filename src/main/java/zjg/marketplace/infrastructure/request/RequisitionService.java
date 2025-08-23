package zjg.marketplace.infrastructure.request;

import reactor.core.publisher.Mono;

public interface RequisitionService {
    Mono<Void> post(String url, Object obj);
}
