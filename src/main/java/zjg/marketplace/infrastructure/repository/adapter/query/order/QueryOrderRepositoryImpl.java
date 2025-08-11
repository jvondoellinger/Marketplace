package zjg.marketplace.infrastructure.repository.adapter.query.order;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.exceptions.validations.order.OrderNotFoundException;
import zjg.marketplace.core.interfaces.services.logging.Logger;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;
import zjg.marketplace.infrastructure.repository.interfaces.OrderReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class QueryOrderRepositoryImpl implements QueryRepository<Order> {
    private final OrderReactiveMongoRepository jpaRepository;
    private final Logger logger;
    private final ReactiveMongoTemplate template;
    private static final Mono<Order> notFoundError = Mono.error(new OrderNotFoundException("Any order is found by this identifier!"));
    private static final Class<QueryOrderRepositoryImpl> self = QueryOrderRepositoryImpl.class;
    public QueryOrderRepositoryImpl(OrderReactiveMongoRepository jpaRepository, Logger logger, ReactiveMongoTemplate template) {
        this.jpaRepository = jpaRepository;
        this.logger = logger;
        this.template = template;
    }

    @Override
    public Mono<Order> findById(String id) {
        logger.info(self, RepositoryMessageUtils.infoQuery(id));
        return jpaRepository.findById(id)
                .switchIfEmpty(notFoundError)
                .doOnNext(x -> logger.info(self, RepositoryMessageUtils.successQuery(id)))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorQuery(id, x.getMessage())));
    }

    @Override
    public Flux<Order> findWithPagination(Long offset, Integer limit) {
        logger.info(self, RepositoryMessageUtils.infoQueryByOffsetAndLimit(offset, limit));
        var query = new Query().skip(offset).limit(limit);
        return template.find(query, Order.class)
                .doOnNext( x -> logger.info(self, RepositoryMessageUtils.successQueryByOffsetAndLimit(offset, limit)))
                .doOnError( x -> logger.error(self, RepositoryMessageUtils.errorQueryByOffsetAndLimit(offset, limit)));
    }
}
