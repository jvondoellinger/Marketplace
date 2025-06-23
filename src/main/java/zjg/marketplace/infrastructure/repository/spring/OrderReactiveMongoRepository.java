package zjg.marketplace.infrastructure.repository.spring;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import zjg.marketplace.core.entity.order.Order;

public interface OrderReactiveMongoRepository extends ReactiveMongoRepository<Order, String> {
}
