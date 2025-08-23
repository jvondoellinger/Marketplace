package zjg.marketplace.payment.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.payment.entity.PixPayment;
import zjg.marketplace.core.payment.services.IPixPaymentProcessor;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.entity.UserBuilder;
import zjg.marketplace.user.factory.UserFactoryTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentServiceTest {

      private static List<Order> orders;
      private static final User userTest = UserFactoryTest.factoryEntity();

      private final IPixPaymentProcessor processor;
      private final FindService<User> findUserService;
      private final FindService<Order> findOrderService;
      @Autowired
      public PaymentServiceTest(IPixPaymentProcessor processor, ServiceResolverFacade facade) {
            this.processor = processor;
            this.findOrderService = facade.resolveFind(Order.class);
            this.findUserService = facade.resolveFind(User.class);
      }

      @Test
      @org.junit.jupiter.api.Order(1)
      public void searchOrder() {
            StepVerifier.create(findOrderService.get(0,5))
                    .recordWith(ArrayList::new)
                    .consumeRecordedWith(order -> {
                          orders = order.stream().toList();
                    })
                    .thenConsumeWhile(x -> true)
                    .verifyComplete();
      }


      @Test
      @org.junit.jupiter.api.Order(2)
      public void generatePixQrCode() {
            for (var order : orders) {
                  StepVerifier.create(processor.generateQrCodeToOrder(order, userTest))
                          .thenConsumeWhile(x -> true)
                          .verifyComplete();
            }
      }


}
