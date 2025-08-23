package zjg.marketplace.infrastructure.repository.adapter.query.product;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.logging.services.Logger;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.repository.exceptions.ProductNotFoundException;
import zjg.marketplace.infrastructure.repository.interfaces.ProductReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class ProductRepositoryQueryImpl implements zjg.marketplace.core.product.repository.query.ProductRepositoryQuery {
    private final ProductReactiveMongoRepository jpaRepository;
    private final Logger logger;
    private final ReactiveMongoTemplate template;
    private static final Class<ProductRepositoryQueryImpl> self = ProductRepositoryQueryImpl.class;
    private static final Mono<Product> notFoundError = Mono.error(new ProductNotFoundException("Any product is found by this identifier!"));
    public ProductRepositoryQueryImpl(ProductReactiveMongoRepository jpaRepository, Logger logger, ReactiveMongoTemplate template) {
        this.jpaRepository = jpaRepository;
        this.logger = logger;
        this.template = template;
    }

    @Override
    public Mono<Product> findById(String id) {
        logger.info(self, RepositoryMessageUtils.infoQuery(id));
        return jpaRepository.findById(id)
                .switchIfEmpty(notFoundError)
                .doOnNext(x -> logger.info(self, RepositoryMessageUtils.successQuery(id)))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorQuery(id, x.getMessage())));
    }

    @Override
    public Mono<Boolean> exists(String id) {
        return  jpaRepository.existsById(id);
    }

    @Override
    public Flux<Product> findWithPagination(long offset, int limit) {
        logger.info(self, RepositoryMessageUtils.infoQueryByOffsetAndLimit(offset, limit));
        var query = new Query().skip(offset).limit(limit);
        return template.find(query, Product.class)
                .doOnNext( x -> logger.info(self, RepositoryMessageUtils.successQueryByOffsetAndLimit(offset, limit)))
                .doOnError( x -> logger.error(self, RepositoryMessageUtils.errorQueryByOffsetAndLimit(offset, limit)));
    }
}
