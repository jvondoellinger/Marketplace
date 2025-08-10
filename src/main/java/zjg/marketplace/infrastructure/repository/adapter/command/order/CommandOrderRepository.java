package zjg.marketplace.infrastructure.repository.adapter.command.order;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.infrastructure.repository.interfaces.OrderReactiveMongoRepository;

@Repository
public class CommandOrderRepository implements CommandRepository<Order> {
    private final OrderReactiveMongoRepository jpaRepository;

    public CommandOrderRepository(OrderReactiveMongoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Mono<Order> insert(Order entity) {
        return jpaRepository.insert(entity);
    }

    @Override
    public Mono<Order> update(Order updated) {
        return jpaRepository.save(updated);
    }

    @Override
    public Mono<Void> delete(String id) {
        return jpaRepository.deleteById(id);
    }
}
