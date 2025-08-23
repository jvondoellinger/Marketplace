package zjg.marketplace.application.services.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.BlocKTimeOutConfig;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.order.entity.Order;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderQueryServiceTest {
    @Autowired
    private ServiceResolverFacade facade;

    private FindService<Order> findService;
    private static final List<String> ids = new ArrayList<>();

    @BeforeEach
    public void initialize() {
        findService = facade.resolveFind(Order.class);
    }
    @Test
    @org.junit.jupiter.api.Order(1)
    public void queryOffset() {
        // * First query / From Database
        StepVerifier.create(findService.get(0, 100))
                .recordWith(ArrayList::new)
                .consumeRecordedWith(x -> {
                    var list = x.stream().map(BaseEntity::getId).toList();
                    ids.addAll(list);
                })
                .thenConsumeWhile(user -> true)
                .verifyComplete();

        // * Second query / From Cache
        StepVerifier.create(findService.get(0, 100))
                .thenConsumeWhile(user -> true)
                .verifyComplete();
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void existsQueried() {
        for (var id : ids) {
            // * First query / From Database
            StepVerifier.create(findService.exists(id))
                    .expectNext(true)
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();

            // * Second query / From Cache
            StepVerifier.create(findService.exists(id))
                    .expectNext(true)
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        }
    }
}
