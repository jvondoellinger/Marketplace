package zjg.marketplace.infrastructure.repository.adapter.query.product;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;

@Repository
public class QueryProductRepositoryImpl implements QueryRepository<Product> {
    @Override
    public Mono<Product> findById(String id) {
        return null;
    }

    @Override
    public Flux<Product> findWithPagination(Long offset, Integer limit) {
        return null;
    }
}
