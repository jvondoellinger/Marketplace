package zjg.marketplace.core.order.strategies.valdiators;

import zjg.marketplace.core.order.strategies.valdiators.exceptions.EmptyProductsIdException;
import zjg.marketplace.core.order.strategies.valdiators.exceptions.NullProductsIdException;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.strategy.interfaces.Validator;

import java.util.Objects;

public class ProductsIdValidator implements Validator<Order> {
    private final static String NULL_MESSAGE = "Products Id cannot be null!";
    private final static String EMPTY_MESSAGE = "Products Id cannot be empty!";
    @Override
    public void validate(Order order) {
        var ids = order.getProductsId();
        if(Objects.isNull(ids)) throw new NullProductsIdException(NULL_MESSAGE);
        if(ids.isEmpty()) throw new EmptyProductsIdException(EMPTY_MESSAGE);
    }
}
