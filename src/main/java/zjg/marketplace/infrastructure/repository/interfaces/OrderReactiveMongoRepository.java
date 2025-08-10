package zjg.marketplace.infrastructure.repository.interfaces;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import zjg.marketplace.core.entity.order.Order;

@Repository
public interface OrderReactiveMongoRepository extends ReactiveMongoRepository<Order, String> {
    Flux<Order> findByBuyerId(String buyerId);
}
