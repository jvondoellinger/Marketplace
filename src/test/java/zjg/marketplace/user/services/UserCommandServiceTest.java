package zjg.marketplace.user.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.resolver.facade.ServiceResolver;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.user.factory.UserFactoryTest;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserCommandServiceTest {
      private final FindService<User> findService;
      private final CreateService<User, UserInput> createService;
      private final DeleteService<User> deleteService;
      private final UpdateService<User, UserInput> updateService;
      private static List<String> userIds = new ArrayList<>();
      @Autowired
      public UserCommandServiceTest(ServiceResolver facade) {
            findService = facade.resolveFind(User.class);
            deleteService = facade.resolveDelete(User.class);
            createService = facade.resolveCreate(User.class, UserInput.class);
            updateService = facade.resolveUpdate(User.class, UserInput.class);
      }

      @Test
      @Order(1)
      public void insertAndRemove() {
            var input = UserFactoryTest.factoryInput();
            for (int i = 0; i < 10; i++) {
                  var mono = createService.create(input).flatMap(u -> deleteService.deleteById(u.getId()));
                  StepVerifier.create(mono).verifyComplete();
            }
      }
/*      @Test
      @Order(2)
      public void addifNotExists() {
            StepVerifier.create(findService.get(0,1))
                    .expectNext(null)
                    .verifyComplete();
      }*/
}
// Fazer uma classe genreciadora de eventos, juntamente com uma classe xEvent que recebe a propria entidade!
