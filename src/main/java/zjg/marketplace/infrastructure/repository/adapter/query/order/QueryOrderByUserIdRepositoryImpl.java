package zjg.marketplace.infrastructure.repository.adapter.query.order;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.exceptions.validations.order.OrderNotFoundException;
import zjg.marketplace.core.interfaces.services.logging.Logger;
import zjg.marketplace.core.interfaces.services.repository.query.QueryByUserIdRepository;
import zjg.marketplace.infrastructure.repository.interfaces.OrderReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class QueryOrderByUserIdRepositoryImpl implements QueryByUserIdRepository<Order> {
    private final OrderReactiveMongoRepository jpaRepository;
    private final Logger logger;
    private final static Mono<Order> notFoundError = Mono.error(new OrderNotFoundException("Order not found by this buyer identifier!"));
    private static final Class<QueryOrderByUserIdRepositoryImpl> self = QueryOrderByUserIdRepositoryImpl.class;
    public QueryOrderByUserIdRepositoryImpl(OrderReactiveMongoRepository jpaRepository, Logger logger) {
        this.jpaRepository = jpaRepository;
        this.logger = logger;
    }

    @Override
    public Flux<Order> findByUserId(String userId) {
        return jpaRepository.findByBuyerId(userId)
                .switchIfEmpty(notFoundError)
                .doOnNext(entity -> logger.info(self, RepositoryMessageUtils.successQuery(userId)))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorQuery(userId, x.getMessage())));
    }
}
