package zjg.marketplace.application.service.order;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.order.OrderInput;
import zjg.marketplace.application.mapper.OrderMapper;
import zjg.marketplace.application.service.helper.FindUserAndProductsHelper;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.order.entity.Order;

@Service
public class CreateOrder implements CreateService<Order, OrderInput> {
      private final CommandRepository<Order> command;
      private final FindUserAndProductsHelper findUserAndProductsHelper;
    public  CreateOrder(CommandRepository<Order> command, FindUserAndProductsHelper findUserAndProductsHelper) {
        this.command = command;
        this.findUserAndProductsHelper = findUserAndProductsHelper;
    }

    @Override
    public Mono<Order> create(OrderInput orderInput) {
        return findUserAndProductsHelper
                .existsUserAndProduct(orderInput.getUserId(), orderInput.setProductId())
                .flatMap(products -> {
                   var mapped = OrderMapper.map(orderInput.getUserId(), products);
                   return command.insert(mapped);
                });
    }
}
