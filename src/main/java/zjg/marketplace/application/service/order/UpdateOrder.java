package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.order.OrderInput;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.mapper.OrderMapper;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.chain.OrderUpdaterHandleFactory;
import zjg.marketplace.core.order.chain.OrderValidatorHandleFactory;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;

@Service
public class UpdateOrder implements UpdateService<Order, OrderUpdateInput> {
    private final FindService<Order> findService;
    private final CommandRepository<Order> command;
    public UpdateOrder(FindService<Order> findService, CommandRepository<Order> command) {
        this.findService = findService;
        this.command = command;
    }

    @Override
    @CacheEvict(value = "order", key = "#id")
    public Mono<Order> update(OrderUpdateInput orderUpdateInput, String id) {
        return Mono.empty();
    }
}
