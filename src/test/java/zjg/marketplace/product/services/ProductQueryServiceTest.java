package zjg.marketplace.product.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.product.entity.Product;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductQueryServiceTest {
    private FindService<Product> findService;
    private static List<String> productId = new ArrayList<>();

    @Autowired
    public ProductQueryServiceTest(ServiceResolverFacade facade) {
        findService = facade.resolveFind(Product.class);
    }

    @Test
    @Order(1)
    public void queryOffsetNoCache() {
        // * First query from database
        StepVerifier.create(findService.get(0, 100))
                .recordWith(ArrayList::new)
                .consumeRecordedWith(x -> {
                    var ids =x.stream().map(BaseEntity::getId).toList();
                    productId.addAll(ids);
                })
                .thenConsumeWhile(user -> true)
                .verifyComplete();;

        // * Second query from cache
        StepVerifier.create(findService.get(0, 100))
                .thenConsumeWhile(user -> true)
                .verifyComplete();
    }

    @Test
    @Order(2)
    public void findByIds() {
        for (var id : productId) {
            // * First query from database
            StepVerifier.create(findService.exists(id))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();

            // * Second query from cache
            StepVerifier.create(findService.exists(id))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        }
    }



    void throwIfFindServiceIsNull() {
        if (findService == null) throw new NullPointerException("Find service is null! Origin: " + this.getClass());
    }
}
