package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.mapper.OrderMapper;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.IUpdateService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.factory.chain.updater.OrderUpdaterHandleFactory;
import zjg.marketplace.core.factory.chain.validators.OrderValidatorHandleFactory;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;

@Service
public class UpdateOrder implements IUpdateService<Order, OrderUpdateInput> {
    private final FindService<Order> findService;
    private final CommandRepository<Order> command;
    public UpdateOrder(FindService<Order> findService, CommandRepository<Order> command) {
        this.findService = findService;
        this.command = command;
    }

    @Override
    @CacheEvict(value = "order", key = "#id")
    public Mono<Order> update(OrderUpdateInput orderUpdateInput, String id) {
        var updateHandler = OrderUpdaterHandleFactory.factory();
        var validateHandler = OrderValidatorHandleFactory.factory();
        return findService.findById(id)
                .flatMap(order -> {
                    var mapped = OrderMapper.unsafeMap(orderUpdateInput);
                    updateHandler.handle(order, mapped);
                    validateHandler.handle(order);
                    return command.update(order);
                });
    }
}
