package zjg.marketplace.application.services;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.entity.product.Product;
import java.time.Duration;
import java.util.List;

@SpringBootTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class ProductQueryServiceTest {

    @Autowired
    public ServiceResolverFacade facade;
    private FindService<Product> findService;
    public List<Product> products;

    @Test
    @Order(0)
    void contextLoads() {
        findService = facade.resolveFind(Product.class);
    }

    @Test
    @Order(1)
    public void queryOffsetWhenClearedCache() {
        throwIfFindServiceIsNull();
        products = get().get(0, 100)
                .collectList()
                .block(Duration.ofSeconds(10));
        if (products.isEmpty()) System.out.println("Porra");
    }

    @Test
    @Order(2)
    public void queryByIdWhenClearedCache() {

        products.forEach(x -> {
            get().findById(x.getId())
                    .block(Duration.ofSeconds(10));
        });
    }

    @Test
    @Order(3)
    public void queryOffsetWhenCache() {
        //throwIfFindServiceIsNull();
        get().get(0, 100)
                .collectList()
                .block(Duration.ofSeconds(10));
    }

    @Test
    @Order(4)
    public void findByIdWhenCache() {
        throwIfFindServiceIsNull();
        products.forEach(x -> {
            get().findById(x.getId());
        });
    }

    FindService<Product> get() {
        return facade.resolveFind(Product.class);
    }

    void throwIfFindServiceIsNull() {
        if (findService == null) throw new NullPointerException("Find service is null! Origin: " + this.getClass());
    }
}
