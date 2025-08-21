package zjg.marketplace.application.services.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.order.entity.Order;

import java.time.Duration;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderQueryServiceTest {
    @Autowired
    public OrderQueryServiceTest(ServiceResolverFacade facade) {
        findService = facade.resolveFind(Order.class);
    }
    private static Duration max = Duration.ofSeconds(5);
    private FindService<Order> findService;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void queryOffsetNoCache() {
        findService.get(0, 1000)
                .collectList()
                .block(max);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void queryOffsetCaching() {
        findService.get(0, 1000)
                .collectList()
                .block(max);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void queryOffsetCached() {
        findService.get(0, 1000)
                .collectList()
                .block(max);
    }
}
