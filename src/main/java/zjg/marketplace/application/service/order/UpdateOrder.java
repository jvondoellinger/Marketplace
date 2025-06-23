package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.service.helper.FindUserAndProductsHelper;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.application.service.promisse.IUpdateService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.factory.chain.updater.OrderUpdaterHandleFactory;
import zjg.marketplace.core.factory.chain.validators.OrderValidatorHandleFactory;
import zjg.marketplace.core.factory.order.OrderFactory;
import zjg.marketplace.core.interfaces.services.repository.IRepository;

@Service
public class UpdateOrder implements IUpdateService<Order, OrderUpdateInput> {
    private final IFindService<Order> findService;
    private final IRepository<Order> repository;
    private final FindUserAndProductsHelper findUserAndProductsHelper;
    public UpdateOrder(IFindService<Order> findService, IRepository<Order> repository, FindUserAndProductsHelper findUserAndProductsHelper) {
        this.findService = findService;
        this.repository = repository;
        this.findUserAndProductsHelper = findUserAndProductsHelper;
    }

    @Override
    @CacheEvict(value = "order", key = "#id")
    public Mono<Order> update(OrderUpdateInput orderUpdateInput, String id) {
        var updateHandler = OrderUpdaterHandleFactory.factory();
        var validateHandler = OrderValidatorHandleFactory.factory();
        return findService.findById(id)
                .flatMap(order ->
                    findUserAndProductsHelper.findUserAndProducts(order.getBuyerId(), orderUpdateInput.getProductId())
                            .flatMap(pair -> {
                                var mapped = OrderFactory.factory(pair.getFirst().getId(), pair.getSecond());
                                updateHandler.handle(order, mapped);
                                validateHandler.handle(order);
                                return repository.update(order);
                            })
                );
    }
}
