package zjg.marketplace.product.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.product.factory.ProductInputFactoryTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductCommandServiceTest {

    @Autowired
    public ServiceResolverFacade facade;
    private FindService<Product> fService;
    private CreateService<Product, ProductInput> createService;
    private UpdateService<Product, ProductInput> updateService;
    private DeleteService<Product> deleteService;
    private static ConcurrentLinkedQueue<String> ids = new ConcurrentLinkedQueue<>();

    @BeforeEach
    void contextLoads() {
        updateService = facade.resolveUpdate(Product.class, ProductInput.class);
        createService = facade.resolveCreate(Product.class, ProductInput.class);
        deleteService = facade.resolveDelete(Product.class);
        fService = facade.resolveFind(Product.class);
    }
    @Test
    @Order(1)
    public void insertItems() {
        for (int i = 1; i < 500; i++) {
            var input = ProductInputFactoryTest.factory();
            StepVerifier.create(createService.create(input))
                    .recordWith(ArrayList::new)
                    .consumeRecordedWith(x -> ids.add(x.stream().findFirst().orElseThrow().getId()))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        }
    }

    @Test
    @Order(2)
    public void putAll() {
        for (var id : ids) {
            var input = ProductInputFactoryTest.factory();
            input.setTitle("System - refresh");
            input.setDescription("System - refresh");
            input.setAmount(BigDecimal.TEN);
            StepVerifier.create(updateService.update(input, id))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        }
    }

    @Test
    @Order(3)
    public void removeAll() {
        for (var id : ids) {
            StepVerifier.create(deleteService.deleteById(id))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();
        };
    }
}
