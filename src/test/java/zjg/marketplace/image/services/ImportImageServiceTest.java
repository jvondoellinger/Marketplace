package zjg.marketplace.image.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.core.image.service.StorageService;
import zjg.marketplace.image.factory.BasicImageFIleFactoryTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImportImageServiceTest {
      private final StorageService service;

      @Autowired
      public ImportImageServiceTest(StorageService service) {
            this.service = service;
      }

      @Test
      @Order(1)
      public void importTestImage() {
            var file = BasicImageFIleFactoryTest.factory();
            StepVerifier.create(service.upload(file))
                    .thenConsumeWhile(x ->  true)
                    .verifyComplete();
      }
      @Test
      @Order(2)
      public void readTestImage() {
            var file = BasicImageFIleFactoryTest.factory();
            StepVerifier.create(service.get(file.getPath()))
                    .thenConsumeWhile(x ->  true)
                    .verifyComplete();
      }
      @Test
      @Order(3)
      public void removeTestImage() {
            var file = BasicImageFIleFactoryTest.factory();
            StepVerifier.create(service.remove(file.getPath()))
                    .thenConsumeWhile(x ->  true)
                    .verifyComplete();
      }
}
