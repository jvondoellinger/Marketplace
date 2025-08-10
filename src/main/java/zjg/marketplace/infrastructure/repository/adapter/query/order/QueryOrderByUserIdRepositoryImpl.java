package zjg.marketplace.infrastructure.repository.adapter.query.order;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.query.QueryByUserIdRepository;
import zjg.marketplace.infrastructure.repository.interfaces.OrderReactiveMongoRepository;

@Repository
public class QueryOrderByUserIdRepositoryImpl implements QueryByUserIdRepository<Order> {
    private final OrderReactiveMongoRepository jpaRepository;

    public QueryOrderByUserIdRepositoryImpl(OrderReactiveMongoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Flux<Order> findByUserId(String userId) {
        return jpaRepository.findByBuyerId(userId);
    }
}
