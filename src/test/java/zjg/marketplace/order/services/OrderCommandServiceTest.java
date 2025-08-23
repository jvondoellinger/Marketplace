package zjg.marketplace.order.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.test.StepVerifier;
import zjg.marketplace.application.dto.order.OrderInput;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.logging.enums.OrderStatusEnum;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.order.factory.OrderInputFactoryTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderCommandServiceTest {
    @Autowired
    public OrderCommandServiceTest(ServiceResolverFacade facade) {
        createService = facade.resolveCreate(Order.class, OrderInput.class);
        updateService = facade.resolveUpdate(Order.class, OrderUpdateInput.class);
        findService = facade.resolveFind(Order.class);
        findProductService = facade.resolveFind(Product.class);
        findUserService = facade.resolveFind(User.class);
        deleteService = facade.resolveDelete(Order.class);
    }
    private static final List<String> productIds = new ArrayList<>();
    private static final List<String> orderIds = new ArrayList<>();
    private static String userId;

    private final FindService<Order> findService;
    private final FindService<Product> findProductService;
    private final FindService<User> findUserService;
    private final CreateService<Order, OrderInput> createService;
    private final UpdateService<Order, OrderUpdateInput> updateService;
    private final DeleteService<Order> deleteService;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void get500Products() {
        StepVerifier.create(findProductService.get(0, 100))
                .recordWith(ArrayList::new)
                .consumeRecordedWith(x -> {
                    var list = x.stream().map(BaseEntity::getId).toList();
                    productIds.addAll(list);
                })
                .thenConsumeWhile(user -> true)
                .verifyComplete();
    }
    @Test
    @org.junit.jupiter.api.Order(2)
    public void get1User() {
        StepVerifier.create(findUserService.get(0, 1))
                .recordWith(ArrayList::new)
                .consumeRecordedWith(x -> {
                    var list = x.stream().map(BaseEntity::getId).toList();
                    userId = list.getFirst();
                })
                .thenConsumeWhile(user -> true)
                .verifyComplete();;
    }
    @Test
    @org.junit.jupiter.api.Order(3)
    public void insert500() {
        var input = OrderInputFactoryTest.factory(userId, productIds);
        for (var id : productIds) {
            StepVerifier.create(createService.create(input))
                    .recordWith(ArrayList::new)
                    .consumeRecordedWith(x -> {
                        var list = x.stream().map(BaseEntity::getId).toList();
                        orderIds.addAll(list);
                    })
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        }
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void updateAllAdded() {
        var input = new OrderUpdateInput();
        input.setStatus(OrderStatusEnum.CANCELED);
        for (var id : orderIds) {
            StepVerifier.create(updateService.update(input, id))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        }
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void removeAllAdded() {
        for (var id : orderIds) {
            StepVerifier.create(deleteService.deleteById(id))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        }
    }
}
