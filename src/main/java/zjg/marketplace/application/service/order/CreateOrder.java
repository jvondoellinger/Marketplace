package zjg.marketplace.application.service.order;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.helper.FindUserAndProductsHelper;
import zjg.marketplace.application.service.promisse.ICreateService;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.entity.OrderFactory;
import zjg.marketplace.application.dto.order.OrderInput;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;

@Service
public class CreateOrder implements ICreateService<Order, OrderInput> {
    private final CommandRepository<Order> command;
    private final FindUserAndProductsHelper findUserAndProductsHelper;
    public  CreateOrder(CommandRepository<Order> command, FindUserAndProductsHelper findUserAndProductsHelper) {
        this.command = command;
        this.findUserAndProductsHelper = findUserAndProductsHelper;
    }

    @Override
    public Mono<Order> create(OrderInput orderInput) {
        return findUserAndProductsHelper
                .findUserAndProducts(orderInput.getUserId(), orderInput.getProductId())
                .flatMap(pair -> {
                    var order = OrderFactory.factory(pair.getFirst().getId(), pair.getSecond());
                    return command.insert(order);
                });
    }
}
