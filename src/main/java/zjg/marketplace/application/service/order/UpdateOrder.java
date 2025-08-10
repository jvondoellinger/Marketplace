package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.mapper.OrderMapper;
import zjg.marketplace.application.service.helper.FindUserAndProductsHelper;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.application.service.promisse.IUpdateService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.factory.chain.updater.OrderUpdaterHandleFactory;
import zjg.marketplace.core.factory.chain.validators.OrderValidatorHandleFactory;
import zjg.marketplace.core.factory.order.OrderFactory;
import zjg.marketplace.core.interfaces.services.repository.Repository;

@Service
public class UpdateOrder implements IUpdateService<Order, OrderUpdateInput> {
    private final IFindService<Order> findService;
    private final Repository<Order> repository;
    public UpdateOrder(IFindService<Order> findService, Repository<Order> repository) {
        this.findService = findService;
        this.repository = repository;
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
                    return repository.update(order);
                });
    }
}
