package zjg.marketplace.application.services.product;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.product.entity.Product;
import java.time.Duration;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductQueryServiceTest {

    @Autowired
    public ServiceResolverFacade facade;
    private FindService<Product> findService;

    @BeforeEach
    void contextLoads() {
        findService = facade.resolveFind(Product.class);
    }

    @Test
    @Order(1)
    public void queryOffsetNoCache() {
        findService.get(0, 1000).collectList().block(Duration.ofSeconds(10));
    }

    @Test
    @Order(2)
    public void queryOffsetCaching() {
        findService.get(0, 1000).collectList().block(Duration.ofSeconds(10));
    }
    @Test
    @Order(3)
    public void queryOffsetCached() {
        findService.get(0, 1000).collectList().block(Duration.ofSeconds(10));
    }



    void throwIfFindServiceIsNull() {
        if (findService == null) throw new NullPointerException("Find service is null! Origin: " + this.getClass());
    }
}
