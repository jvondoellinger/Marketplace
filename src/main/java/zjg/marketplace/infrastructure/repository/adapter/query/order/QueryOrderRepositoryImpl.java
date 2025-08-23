package zjg.marketplace.infrastructure.repository.adapter.query.order;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.logging.services.Logger;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.repository.exceptions.OrderNotFoundException;
import zjg.marketplace.core.order.repository.query.OrderRepositoryQuery;
import zjg.marketplace.infrastructure.repository.interfaces.OrderReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class QueryOrderRepositoryImpl implements OrderRepositoryQuery {
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

    // Adicionar um padrão observer nos repositorios/serviços, daria um desacoplamento melhor

    @Override
    public Mono<Order> findById(String id) {
        logger.info(self, RepositoryMessageUtils.infoQuery(id));
        return jpaRepository.findById(id)
                .switchIfEmpty(notFoundError)
                .doOnNext(x -> logger.info(self, RepositoryMessageUtils.successQuery(id)))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorQuery(id, x.getMessage())));
    }

    @Override
    public Mono<Boolean> exists(String id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Flux<Order> findWithPagination(long offset, int limit) {
        logger.info(self, RepositoryMessageUtils.infoQueryByOffsetAndLimit(offset, limit));
        var query = new Query().skip(offset).limit(limit);
        return template.find(query, Order.class)
                .doOnNext( x -> logger.info(self, RepositoryMessageUtils.successQueryByOffsetAndLimit(offset, limit)))
                .doOnError( x -> logger.error(self, RepositoryMessageUtils.errorQueryByOffsetAndLimit(offset, limit)));
    }

    @Override
    public Flux<Order> findByUserId(String userId) {
        return jpaRepository.findByBuyerId(userId)
                .doOnNext( x -> logger.info(self, RepositoryMessageUtils.successQueryByUserId(userId)))
                .doOnError( x -> logger.error(self, RepositoryMessageUtils.errorQueryByUserId(userId)));
    }
}
