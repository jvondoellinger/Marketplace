package zjg.marketplace.application.services.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zjg.marketplace.application.dto.order.OrderInput;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.logging.enums.OrderStatusEnum;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.user.entity.User;

import java.time.Duration;
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
    private static final Duration limit = Duration.ofSeconds(5);
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
        var list = findProductService.get(0, 500)
                .map(Product::getId)
                .collectList()
                .block(limit);
        productIds.addAll(list);
    }
    @Test
    @org.junit.jupiter.api.Order(2)
    public void get1User() {
        userId =  findUserService.get(0,1)
                .collectList()
                .block(limit)
                .get(0)
                .getId();
    }
    @Test
    @org.junit.jupiter.api.Order(3)
    public void insert500() {
        for (var id : productIds) {
            var input = new OrderInput();
            System.out.println(id);
            input.setProductInput(productIds);
            input.setUserId(userId);
            var o = createService.create(input).block(limit);
            orderIds.add(o.getId());
        }
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void updateAllAdded() {
        for (var id : orderIds) {
            var input = new OrderUpdateInput();
            input.setStatus(OrderStatusEnum.CANCELED);
            updateService.update(input, id).block(limit);
        }
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void removeAllAdded() {
        for (var id : orderIds) {
            deleteService.deleteById(id).block(limit);
        }
    }
}
