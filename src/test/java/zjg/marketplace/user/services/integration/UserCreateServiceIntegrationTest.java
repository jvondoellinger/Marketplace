package zjg.marketplace.user.services.integration;

import com.mongodb.DuplicateKeyException;
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
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.valueObj.cpf.CPF;
import zjg.marketplace.core.user.valueObj.phone.PhoneNumber;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// ! Integration Test
public class UserCreateServiceIntegrationTest {
      private final CreateService<User, UserInput> createService;
      private final StoreUserInputs storeUserInputs;

      @Autowired
      public UserCreateServiceIntegrationTest(ServiceResolver facade) {
            this.createService = facade.resolveCreate(User.class, UserInput.class);
            this.storeUserInputs = new StoreUserInputs();
      }
      // * Custom class
      protected static class StoreUserInputs {
            protected static UserInput sharedInput;

            protected StoreUserInputs() {
                  populate();
            }

            private void populate() {
                  var input = new UserInput();
                  var fakeDocument = new CPF();
                  var date = new Date();
                  var phoneNumber = new PhoneNumber();
                  fakeDocument.setCpf("63033923429");
                  date.setYear(date.getYear() - 20);

                  phoneNumber.setNumber("000000000");
                  phoneNumber.setAreaCode("21");
                  phoneNumber.setCountryCode("55");

                  input.setUsername("System");
                  input.setDocument(fakeDocument);
                  input.setEmail("system@fake.com");
                  input.setPassword("AnyPassword123@@");

                  input.setBirthDay(date);
                  input.setPhone(phoneNumber);
                  sharedInput = input;
            }

            protected static void setSharedInput(UserInput sharedInput) {
                  StoreUserInputs.sharedInput = sharedInput;
            }
      }

      // * Insert a predefined user
      @Test
      @Order(1)
      public void insert() {
            StepVerifier.create(createService.create(StoreUserInputs.sharedInput))
                    .thenConsumeWhile(x -> true)
                    .verifyComplete();
      }

      // * Verify if generate an exception by duplicate key (email, username, phone or document)
      @Test
      @Order(2)
      public void shouldErrorByDuplicatedKey() {
            StepVerifier.create(createService.create(StoreUserInputs.sharedInput))
                    .thenConsumeWhile(x -> true)
                    .expectErrorSatisfies(error -> {
                          assertThat(error)
                                  .isInstanceOfAny(DuplicateKeyException.class);
                    })
                    .verify();
      }

}
