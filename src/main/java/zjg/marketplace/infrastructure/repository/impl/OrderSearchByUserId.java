package zjg.marketplace.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.SearchByUserIdRepository;
import zjg.marketplace.infrastructure.repository.spring.OrderReactiveMongoRepository;

@Repository
public class OrderSearchByUserId implements SearchByUserIdRepository<Order> {
    public final OrderReactiveMongoRepository repositoryLib;

    public OrderSearchByUserId(OrderReactiveMongoRepository repositoryLib) {
        this.repositoryLib = repositoryLib;
    }

    @Override
    public Flux<Order> findByUserId(String userId) {
        return repositoryLib.findByUserId(userId);
    }
}
