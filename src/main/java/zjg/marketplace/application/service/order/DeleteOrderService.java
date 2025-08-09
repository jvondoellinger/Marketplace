package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IDeleteService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.Repository;

@Service
public class DeleteOrderService implements IDeleteService<Order> {
    private final Repository<Order> repository;

    public DeleteOrderService(Repository<Order> repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict
    public Mono<Void> delete(Order order) {
        return repository.delete(order.getId());
    }

    @Override
    @CacheEvict(value = "order", key = "#id")
    public Mono<Void> deleteById(String id) {
        return repository.delete(id);
    }
}
