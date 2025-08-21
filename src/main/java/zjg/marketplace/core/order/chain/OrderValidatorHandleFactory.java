package zjg.marketplace.core.order.chain;

import zjg.marketplace.core.chain.impl.validators.ValidationHandler;
import zjg.marketplace.core.chain.interfaces.Handler;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.strategies.valdiators.ProductsIdValidator;
import zjg.marketplace.core.strategy.common.AmountValidator;
import zjg.marketplace.core.strategy.common.UserIdValidator;

public class OrderValidatorHandleFactory {
    public static Handler<Order> factory() {
        var chain = new ValidationHandler<Order>();
        chain.setNext(new AmountValidator<Order>());
        chain.setNext(new UserIdValidator<Order>());
        chain.setNext(new ProductsIdValidator());
        return chain;
    }
}
