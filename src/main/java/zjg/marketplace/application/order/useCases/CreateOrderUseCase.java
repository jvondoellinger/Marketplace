package zjg.marketplace.application.order.useCases;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.mapper.OrderMapper;
import zjg.marketplace.application.order.dto.OrderInputDto;
import zjg.marketplace.application.order.dto.OrderOutputDto;
import zjg.marketplace.application.service.helper.FindUserAndProductsHelper;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.infrastructure.repository.adapter.command.order.CommandOrderRepository;

@Service
public class CreateOrderUseCase {
      private final FindUserAndProductsHelper findUserAndProductsHelper;
      private final CommandOrderRepository command;

      public CreateOrderUseCase(FindUserAndProductsHelper findUserAndProductsHelper, CommandOrderRepository command) {
            this.findUserAndProductsHelper = findUserAndProductsHelper;
            this.command = command;
      }

      public Mono<OrderOutputDto> execute(OrderInputDto inputDto) {
            var userId = inputDto.userId();
            return findUserAndProductsHelper
                    .existsUserAndProduct(userId, inputDto.productsId())
                    .flatMap(products -> {
                          var mapped = OrderMapper.map(userId, products);
                          return command.insert(mapped);
                    })
                    .map(OrderMapper::entityToOutput);
      }
}
