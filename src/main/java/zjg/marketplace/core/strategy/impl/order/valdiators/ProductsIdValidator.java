package zjg.marketplace.core.strategy.impl.order.valdiators;

import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.exceptions.validations.order.EmptyProductsIdException;
import zjg.marketplace.core.exceptions.validations.order.NullProductsIdException;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public class ProductsIdValidator implements IValidator<Order> {
    private final static String NULL_MESSAGE = "Products Id cannot be null!";
    private final static String EMPTY_MESSAGE = "Products Id cannot be empty!";
    @Override
    public void validate(Order order) {
        var ids = order.getProductsId();
        if(Objects.isNull(ids)) throw new NullProductsIdException(NULL_MESSAGE);
        if(ids.isEmpty()) throw new EmptyProductsIdException(EMPTY_MESSAGE);
    }
}
