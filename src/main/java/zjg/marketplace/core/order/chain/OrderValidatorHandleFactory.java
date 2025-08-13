package zjg.marketplace.core.factory.chain.validators;

import zjg.marketplace.core.chain.impl.validators.ValidationHandler;
import zjg.marketplace.core.chain.interfaces.Handler;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.strategy.impl.common.validators.AmountValidator;
import zjg.marketplace.core.strategy.impl.common.validators.UserIdValidator;
import zjg.marketplace.core.strategy.impl.order.valdiators.ProductsIdValidator;

public class OrderValidatorHandleFactory {
    public static Handler<Order> factory() {
        var chain = new ValidationHandler<Order>();
        chain.setNext(new AmountValidator<Order>());
        chain.setNext(new UserIdValidator<Order>());
        chain.setNext(new ProductsIdValidator());
        return chain;
    }
}
