package zjg.marketplace.user.services.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.resolver.facade.ServiceResolver;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.strategies.updater.exceptions.*;

import java.util.ArrayDeque;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// ! Integration Test
public class UserUpdateServiceIntegrationTest {
      private final UpdateService<User, UserInput> updateService;
      private final FindService<User> findService;

      private User randomUser;

      @Autowired
      public UserUpdateServiceIntegrationTest(ServiceResolver facade) {
            this.updateService = facade.resolveUpdate(User.class, UserInput.class);
            this.findService = facade.resolveFind(User.class);
      }

      @Test
      @Order(1)
      public void selectRandomUser() {
            StepVerifier.create(findService.get(0,1))
                    .recordWith(ArrayDeque::new)
                    .consumeRecordedWith(collection -> {
                          System.out.println(collection.size());
                          randomUser = collection.stream().toList().getFirst();
                    })
                    .thenConsumeWhile(x -> true)
                    .verifyComplete();
      }

      @Test
      @Order(2)
      public void shouldExceptionBySameData() {
            var input = map(randomUser);
            StepVerifier.create(updateService.update(input, randomUser.getId()))
                    .expectErrorMatches(x -> x instanceof UserUpdaterException)
                    .verify();
      }

      private UserInput map(User user) {
            var input = new UserInput();
            input.setUsername(user.getUsername());
            input.setPhone(user.getPhone());
            input.setEmail(user.getEmail());
            input.setDocument(user.getDocument());
            input.setBirthDay(user.getBirthDay());
            return input;
      }
}