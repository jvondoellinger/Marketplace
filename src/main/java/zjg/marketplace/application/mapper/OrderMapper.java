package zjg.marketplace.application.mapper;

import zjg.marketplace.application.dto.order.OrderUpdateInput;
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
    public static Order unsafeMap(OrderUpdateInput input) {
        return OrderBuilder.builder()
                .status(input.getStatus())
                .build();
    }
}
