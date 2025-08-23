package zjg.marketplace.order.factory;

import zjg.marketplace.application.dto.order.OrderInput;

import java.util.List;

public class OrderInputFactoryTest {
      public static OrderInput factory(String userId, List<String> productIds) {
            var input = new OrderInput();
            input.setProductInput(productIds);
            input.setUserId(userId);
            return input;
      }
}
