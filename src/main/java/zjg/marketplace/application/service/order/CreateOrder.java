package zjg.marketplace.application.service.order;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.helper.FindUserAndProductsHelper;
import zjg.marketplace.application.service.promisse.ICreateService;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.factory.order.OrderFactory;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.application.dto.order.OrderInput;

@Service
public class CreateOrder implements ICreateService<Order, OrderInput> {
    private final Repository<Order> repository;
    private final IFindService<User> findUserService;
    private final IFindService<Product> findProductService;
    private final FindUserAndProductsHelper findUserAndProductsHelper;
    public  CreateOrder(Repository<Order> repository, IFindService<User> findUserService, IFindService<Product> findProductService, FindUserAndProductsHelper findUserAndProductsHelper) {
        this.repository = repository;
        this.findUserService = findUserService;
        this.findProductService = findProductService;
        this.findUserAndProductsHelper = findUserAndProductsHelper;
    }

    @Override
    public Mono<Order> create(OrderInput orderInput) {
        return findUserAndProductsHelper
                .findUserAndProducts(orderInput.getUserId(), orderInput.getProductId())
                .flatMap(pair -> {
                    var order = OrderFactory.factory(pair.getFirst().getId(), pair.getSecond());
                    return repository.insert(order);
                });
    }
}
