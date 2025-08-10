package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IDeleteService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;

@Service
public class DeleteOrderService implements IDeleteService<Order> {
    private final CommandRepository<Order> command;

    public DeleteOrderService(CommandRepository<Order> command) {
        this.command = command;
    }

    @Override
    @CacheEvict
    public Mono<Void> delete(Order order) {
        return command.delete(order.getId());
    }

    @Override
    @CacheEvict(value = "order", key = "#id")
    public Mono<Void> deleteById(String id) {
        return command.delete(id);
    }
}
