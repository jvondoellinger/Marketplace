package zjg.marketplace.user.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.BlocKTimeOutConfig;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserQueryServiceTest {
      private final FindService<User> findService;
      private static List<String> userIds = new ArrayList<>();
      @Autowired
      public UserQueryServiceTest(ServiceResolverFacade facade) {
            findService = facade.resolveFind(User.class);
      }

      @Test
      @Order(1)
      public void queryOffset() {
            // * First call - Database
            StepVerifier.create(findService.get(0, 100))
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();

            // * Second call - Cache
            StepVerifier.create(findService.get(0, 100))
                    .recordWith(ArrayList::new)
                    .consumeRecordedWith(users -> {
                          if (users != null && !users.isEmpty())
                                userIds.addAll(users.stream().map(BaseEntity::getId).toList());
                    })
                    .thenConsumeWhile(user -> true)
                    .verifyComplete();

      }

      @Test
      @Order(2)
      public void queryByFoundIds() {
            for (var id : userIds) {
                  // * First query - From Database
                  StepVerifier.create(findService.exists(id))
                          .expectNext(true)
                          .expectComplete()
                          .verify(BlocKTimeOutConfig.limit);

                  // * Second query - From Cache
                  StepVerifier.create(findService.exists(id))
                          .expectNext(true)
                          .expectComplete()
                          .verify(BlocKTimeOutConfig.limit);
            }
      }
}
