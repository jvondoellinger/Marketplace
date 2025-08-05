package zjg.marketplace.core.strategy.impl.order.valdiators;

import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.exceptions.validations.order.BlankBuyerIdException;
import zjg.marketplace.core.exceptions.validations.order.NullBuyerIdException;
import zjg.marketplace.core.strategy.interfaces.IValidator;

import java.util.Objects;

public class BuyerIdValidator implements IValidator<Order> {
    private static final String NULL_MESSAGE = "BuyerId cannot be null!";
    private static final String BLANK_MESSAGE = "BuyerId cannot be blank!";
    @Override
    public void validate(Order order) {
        var buyerId = order.getBuyerId();
        if(Objects.isNull(buyerId)) throw new NullBuyerIdException(NULL_MESSAGE);
        if(buyerId.isBlank()) throw new BlankBuyerIdException(BLANK_MESSAGE);
    }
}
