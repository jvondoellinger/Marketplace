package zjg.marketplace.application.mapper;

import zjg.marketplace.application.order.dto.OrderOutputDto;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.entity.OrderBuilder;
import zjg.marketplace.core.order.entity.OrderFactory;
import zjg.marketplace.core.product.entity.Product;

import java.util.List;

public class OrderMapper {
    private OrderMapper() {}

    public static Order map(String buyerId, List<Product> products) {
        return OrderFactory.factory(buyerId, products);
    }

    public static Order unsafeMap(String buyerId, List<Product> products) {
        return OrderBuilder.builder()
                .buyerId(buyerId)
                .products(products)
                .build();
    }

    public static OrderOutputDto entityToOutput(Order entity) {
        var productIds = entity.getProductsId();
        var state = entity.getState().showState();
        return new OrderOutputDto(productIds, state, entity.getAmount());
    }

}
