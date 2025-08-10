package zjg.marketplace.application.mapper;

import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.order.OrderBuilder;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.factory.order.OrderFactory;

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
    public static Order unsafeMap(OrderUpdateInput input) {
        return OrderBuilder.builder()
                .status(input.getStatus())
                .build();
    }
}
