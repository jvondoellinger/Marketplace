package zjg.marketplace.application.services.user;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.valueObj.cpf.CPF;
import zjg.marketplace.core.user.valueObj.phone.PhoneNumber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserCommandServiceTest {
      private final FindService<User> findService;
      private final CreateService<User, UserInput> createService;
      private final DeleteService<User> deleteService;
      private final UpdateService<User, UserInput> updateService;
      private static List<String> userIds = new ArrayList<>();
      @Autowired
      public UserCommandServiceTest(ServiceResolverFacade facade) {
            findService = facade.resolveFind(User.class);
            deleteService = facade.resolveDelete(User.class);
            createService = facade.resolveCreate(User.class, UserInput.class);
            updateService = facade.resolveUpdate(User.class, UserInput.class);
      }

      @Test
      @Order(1)
      public void insertAndRemove() {
            var input = new UserInput();
            var fakeDocument = new CPF();
            var date = new Date();
            var phoneNumber = new PhoneNumber();
            fakeDocument.setCpf("63033923429");
            date.setYear(date.getYear() - 20);
            phoneNumber.setNumber("909099090");
            phoneNumber.setAreaCode("21");
            phoneNumber.setCountryCode("55");

            input.setUsername("System");
            input.setDocument(fakeDocument);
            input.setEmail("system@fake.com");
            input.setPassword("AnyPassword123@@");

            input.setBirthDay(date);
            input.setPhone(phoneNumber);

            for (int i = 0; i < 10; i++) {
                  var mono = createService.create(input).flatMap(u -> deleteService.deleteById(u.getId()));
                  StepVerifier.create(mono).verifyComplete();
            }
      }
}
